package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.rest.UserInfo;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class UserResponse extends UserData implements UserInfo {

    private Long id;

    public UserResponse() {
    }

    public UserResponse(Long id) {
        this.id = id;
    }

    public UserResponse(Long id, String username, String email,
            String displayName, Long roleId, UserType type,
            List<Long> operations) {
        super(username, email, displayName, roleId, type, operations);
        this.id = id;
    }

    public UserResponse(Long id, String username, String email,
            String displayName, Long roleId, UserType type,
            List<Long> operations, Boolean enabled, Calendar created,
            Calendar updated) {
        super(username, email, displayName, roleId, type, operations, enabled,
                created, updated);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
