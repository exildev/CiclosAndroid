package com.nativapps.arpia.rest;

import com.nativapps.arpia.database.entity.UserType;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface UserInfo {

    Long getId();

    String getUsername();

    String getDisplayName();

    String getEmail();

    UserType getType();

    Long getRoleId();

    Boolean getEnabled();

}
