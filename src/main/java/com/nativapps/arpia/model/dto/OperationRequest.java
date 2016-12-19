package com.nativapps.arpia.model.dto;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0.1
 */
public class OperationRequest extends OperationData {

    private String administrator;

    private String email;

    private String password;

    public OperationRequest() {
    }

    public OperationRequest(String administrator, String email, String password,
            String name, String alias, String description, String imageUrl) {
        super(name, alias, description, imageUrl);
        this.administrator = administrator;
        this.email = email;
        this.password = password;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
