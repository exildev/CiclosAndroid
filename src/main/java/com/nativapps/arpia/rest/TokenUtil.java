package com.nativapps.arpia.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.model.dto.UserResponse;
import com.nativapps.arpia.rest.exception.TokenException;
import com.nativapps.arpia.rest.exception.UnauthorizedException;
import com.nativapps.arpia.rest.io.JwkDeserializer;
import com.nativapps.arpia.rest.io.JwkSerializer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

/**
 * The <strong>TokenUtil</strong> class provided static methods to generate and
 * validate authorization tokens.
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class TokenUtil {

    private static final Logger LOG = Logger
            .getLogger(TokenUtil.class.getName());

    /**
     * key with RSA algorithm.
     */
    private final RsaJsonWebKey jsonWebKey;

    /**
     * GSON Object for Serialize and deserialize a json web key object.
     */
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(JsonWebKey.class, new JwkDeserializer())
            .registerTypeAdapter(JsonWebKey.class, new JwkSerializer())
            .create();

    /**
     * claim name for the user ID.
     */
    private static final String USER_ID = "uid";

    /**
     * claim name for the email.
     */
    private static final String EMAIL = "email";

    /**
     * claim name for the username.
     */
    private static final String USERNAME = "username";

    /**
     * claim name for the display name of user.
     */
    private static final String DISPLAY_NAME = "name";

    /**
     * claim name for the role ID.
     */
    private static final String ROLE_ID = "rid";

    /**
     * claim name for the user type.
     */
    private static final String USER_TYPE = "type";

    /**
     * claim name for the enabled user.
     */
    private static final String ENABLED = "enabled";

    /**
     * error code for generation keys.
     */
    private static final int ERROR_CODE_GENERATE_KEY = 1;

    /**
     * error code for serialization token.
     */
    private static final int ERROR_CODE_SERIALIZE_TOKEN = 2;

    /**
     * error code for formatting payload.
     */
    private static final int ERROR_CODE_FORMAT_PAYLOAD = 3;

    /**
     * Create a new instance of <code>TokenUtil</code>.
     *
     * @throws TokenException if an error occurs.
     */
    private TokenUtil() throws TokenException {
        try {
            jsonWebKey = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new TokenException("Error when trying to create a key wiht "
                    + "RSA algorithm to sign the token.",
                    ERROR_CODE_GENERATE_KEY, ex);
        }
    }

    /**
     * instance of <code>TokenUtil</code>.
     */
    private static TokenUtil instance;

    /**
     * Get instance of <code>TokenUtil</code>.
     *
     * @return instance.
     * @throws TokenException if an error occurs.
     */
    private static TokenUtil instance() throws TokenException {
        if (instance == null)
            instance = new TokenUtil();
        return instance;
    }

    /**
     * Get to key with RSA algorithm.
     *
     * @return key.
     */
    public RsaJsonWebKey getJsonWebKey() {
        return jsonWebKey;
    }

    /**
     * Create a new authorization tokenfrom the given user information.
     *
     * @param userInfo user information.
     * @throws TokenException if an error occurs.
     *
     * @return token generated.
     */
    public static String generateToken(UserInfo userInfo)
            throws TokenException {
        if (userInfo == null)
            throw new NullPointerException("User information can not be null.");

        JwtClaims claims = createPayload(userInfo);

        RsaJsonWebKey jsonKey = instance().getJsonWebKey();

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(jsonKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        try {
            return jws.getCompactSerialization();
        } catch (JoseException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new TokenException("Error serializing the authorization "
                    + "token.", ERROR_CODE_SERIALIZE_TOKEN, ex);
        }
    }

    /**
     * Create payload from given user information.
     *
     * @param userInfo user information.
     * @return claims of json web token.
     */
    private static JwtClaims createPayload(UserInfo userInfo) {
        JwtClaims claims = new JwtClaims();

        claims.setExpirationTimeMinutesInTheFuture(15);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBefore(NumericDate.now());

        claims.setClaim(USER_ID, userInfo.getId());
        claims.setClaim(ROLE_ID, userInfo.getRoleId());
        claims.setClaim(USER_TYPE, userInfo.getType());
        claims.setClaim(DISPLAY_NAME, userInfo.getDisplayName());
        claims.setClaim(USERNAME, userInfo.getUsername());
        claims.setClaim(EMAIL, userInfo.getEmail());
        claims.setClaim(ENABLED, userInfo.getEnabled());

        return claims;
    }

    /**
     * Get user information if the authorization token is valid.
     *
     * @param token authorization token.
     * @throws TokenException if an error ocurred.
     * @throws UnauthorizedException if token is invalid.
     *
     * @return user information.
     */
    public static UserInfo validateToken(String token) throws TokenException,
            UnauthorizedException {
        JwtConsumer consumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setVerificationKey(instance().getJsonWebKey().getKey())
                .build();

        try {
            return getUserInfoFromPayload(consumer.processToClaims(token));
        } catch (InvalidJwtException ex) {
            throw new UnauthorizedException("The auth token is not valid.", ex);
        }
    }

    /**
     * Get user information converting payload to DTO.
     *
     * @param claims payload.
     * @throws TokenException if the values of the payload don't have format
     * correct.
     *
     * @return user information.
     */
    private static UserInfo getUserInfoFromPayload(JwtClaims claims)
            throws TokenException {
        UserInfo userInfo = null;
        try {
            Long userId = claims.getClaimValue(USER_ID, Long.class);
            Long roleId = claims.getClaimValue(ROLE_ID, Long.class);
            String typeString = claims.getClaimValue(USER_TYPE, String.class);
            UserType type = UserType.valueOf(typeString);
            String displayName = claims.getClaimValue(DISPLAY_NAME, String.class);
            String username = claims.getClaimValue(USERNAME, String.class);
            String email = claims.getClaimValue(EMAIL, String.class);
            Boolean enabled = claims.getClaimValue(ENABLED, Boolean.class);

            userInfo = new UserResponse(userId, username, email, displayName,
                    roleId, type, null, enabled, null, null);
        } catch (MalformedClaimException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new TokenException("The claim format is not valid.",
                    ERROR_CODE_FORMAT_PAYLOAD, ex);
        }
        return userInfo;
    }

}
