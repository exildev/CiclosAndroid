package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Role;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface RoleDao extends DataAccessObject<Role, Long> {

    /**
     * Get a role information from the specific name provided.
     * 
     * @param name the specific name.
     * @return role information.
     */
    Role findByName(String name);

}
