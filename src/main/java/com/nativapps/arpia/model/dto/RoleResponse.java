package com.nativapps.arpia.model.dto;

import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class RoleResponse extends RoleData {

    private Long id;

    public RoleResponse() {
    }

    public RoleResponse(Long id, String name, List<PermissionData> permissions) {
        super(name, permissions);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
