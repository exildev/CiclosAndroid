package com.nativapps.arpia.model.dto;

import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class RoleRequest extends RoleData {

    public RoleRequest() {
    }

    public RoleRequest(String name, List<PermissionData> permissions) {
        super(name, permissions);
    }

}
