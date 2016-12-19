package com.nativapps.arpia.model.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo Pacheco <gpacheco@nativapps.mobi>
 * @version 1.0
 */
@XmlRootElement
public class TokenData {

    private String token;

    private final String type = "JWT";

    public TokenData() {
    }

    public TokenData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlElement
    public String getType() {
        return type;
    }

}
