package com.nativapps.arpia.rest.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map;
import org.jose4j.jwk.JsonWebKey;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class JwkSerializer implements JsonSerializer<JsonWebKey> {

    @Override
    public JsonElement serialize(JsonWebKey object, Type type,
            JsonSerializationContext context) {
        Map<String, Object> jwkParameters = object
                .toParams(JsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        return context.serialize(jwkParameters);
    }

}
