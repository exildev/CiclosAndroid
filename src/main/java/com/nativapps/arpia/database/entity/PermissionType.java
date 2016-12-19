package com.nativapps.arpia.database.entity;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public enum PermissionType {

    /**
     * This permission is used to read the given entities.
     */
    READ,

    /**
     * This permission is used to read, add and update the given entities.
     */
    WRITE,

    /**
     * This permission has full control of the entities.
     */
    ADMIN

}
