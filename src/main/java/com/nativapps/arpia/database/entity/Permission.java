package com.nativapps.arpia.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "PERMISSION")
@NamedQueries({
    @NamedQuery(name = "permission.findByRoleId",
            query = "SELECT p FROM Permission p WHERE p.roleId = :roleId"),
    @NamedQuery(name = "permission.deleteByRoleId",
            query = "DELETE FROM Permission p WHERE p.roleId = :roleId")
})
public class Permission implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FUNCTIONALITY")
    @Enumerated(EnumType.STRING)
    private Functionality functionality;

    @Column(name = "PERMISSION_TYPE")
    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    @Column(name = "ROLE_ID", insertable = false, updatable = false,
            nullable = false)
    private long roleId;

    public Permission() {
    }

    public Permission(Functionality functionality,
            PermissionType permissionType) {
        this.functionality = functionality;
        this.permissionType = permissionType;
    }

    public Permission(long id, Functionality functionality,
            PermissionType permissionType, long roleId) {
        this.id = id;
        this.functionality = functionality;
        this.permissionType = permissionType;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
