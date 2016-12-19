package com.nativapps.arpia.database.converter;

import javax.persistence.AttributeConverter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class PasswordConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String password) {
        return DigestUtils.sha1Hex(password);
    }

    @Override
    public String convertToEntityAttribute(String cryptoPassword) {
        return cryptoPassword;
    }

}
