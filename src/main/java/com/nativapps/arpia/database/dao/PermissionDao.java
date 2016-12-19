package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Permission;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface PermissionDao extends DataAccessObject<Permission, Long> {

    /**
     * Get a list with all permissions information from the given role ID.
     * 
     * @param roleId role ID.
     * @return The permission list.
     */
    List<Permission> findAllPermissionsByRoleId(long roleId);
    
    /**
     * Delete all permissions of a role from the given role ID.
     * 
     * @param roleId role ID.
     */
    void deleteAllPermissionByRoleId(long roleId);

}
