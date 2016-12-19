package com.nativapps.arpia.model.dto;

import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class RoleData {

    private String name;

    private List<PermissionData> permissions;

    public RoleData() {
    }

    public RoleData(String name, List<PermissionData> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionData> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionData> permissions) {
        this.permissions = permissions;
    }

}
