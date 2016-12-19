package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.UserType;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@XmlRootElement
public class UserRequest extends UserData {

    private String password;

    public UserRequest() {
    }

    public UserRequest(String password, String username, String email,
            String displayName, Long roleId, UserType type,
            List<Long> operations) {
        super(username, email, displayName, roleId, type, operations);
        this.password = password;
    }

    public UserRequest(String password, String username, String email,
            String displayName, Long roleId, UserType type,
            List<Long> operations, Boolean enabled, Calendar created,
            Calendar updated) {
        super(username, email, displayName, roleId, type, operations,
                enabled, created, updated);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
