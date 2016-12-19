package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Functionality;
import com.nativapps.arpia.database.entity.PermissionType;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class PermissionData {

    private Functionality functionality;

    private PermissionType permissionType;

    public PermissionData() {
    }

    public PermissionData(Functionality functionality,
            PermissionType permissionType) {
        this.functionality = functionality;
        this.permissionType = permissionType;
    }

    public Functionality getFunctionality() {
        return functionality;
    }

    public void setFunctionality(Functionality functionality) {
        this.functionality = functionality;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

}
