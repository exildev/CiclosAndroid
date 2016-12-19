package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.RoleRequest;
import com.nativapps.arpia.model.dto.RoleResponse;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface RoleService extends Service {

    /**
     * Get all roles
     *
     * @return roles information
     */
    List<RoleResponse> getAllRoles();

    /**
     * Get role information by id provided
     *
     * @param id role id
     * @return role data
     */
    RoleResponse getRole(Long id);

    /**
     * Create a new role from given data
     *
     * @param role role data
     * @return role data with id
     */
    RoleResponse add(RoleRequest role);

    /**
     * Modify the data of a role from its id
     *
     * @param id role id
     * @param role role data
     * @return role data updated
     */
    RoleResponse update(Long id, RoleRequest role);

    /**
     * Delete role by id
     *
     * @param id role id
     * @return role deleted
     */
    RoleResponse delete(Long id);

}
