package com.nativapps.arpia.rest.io;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class JwkDeserializer implements JsonDeserializer<JsonWebKey> {

    private static final Logger LOG = Logger
            .getLogger(JwkDeserializer.class.getName());

    @Override
    public JsonWebKey deserialize(JsonElement json, Type type,
            JsonDeserializationContext context) throws JsonParseException {
        Map<String, Object> jwkParameters = context.deserialize(json,
                LinkedHashMap.class);
        try {
            return JsonWebKey.Factory.newJwk(jwkParameters);
        } catch (JoseException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new JsonParseException("Unable to create JWK Object when "
                    + "parsing JSON.", ex);
        }
    }

}
