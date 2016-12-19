package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.PermissionDao;
import com.nativapps.arpia.database.dao.RoleDao;
import com.nativapps.arpia.database.entity.Permission;
import com.nativapps.arpia.database.entity.Role;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.PermissionData;
import com.nativapps.arpia.model.dto.RoleRequest;
import com.nativapps.arpia.model.dto.RoleResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.1.0
 */
public class RoleServiceImpl extends GenericService implements RoleService,
        DtoConverter<Role, RoleRequest, RoleResponse> {

    private final RoleDao roleController = EntityControllerFactory
            .getRoleController();

    private final PermissionDao permissionController = EntityControllerFactory
            .getPermissionController();

    /**
     * Get information of role entity from the given role ID.
     *
     * @param id role ID.
     * @return the role information.
     */
    private Role getEntityRole(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config.getString("role.id_required"));

        Role role = roleController.find(id);
        if (role == null) {
            String msg = String.format(config.getString("role.not_found"), id);
            throw new NotFoundException(msg);
        }

        return role;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> roles = new ArrayList<>();
        for (Role role : roleController.findAll())
            roles.add(convertToDto(role));
        return roles;
    }

    @Override
    public RoleResponse getRole(Long id) {
        return convertToDto(getEntityRole(id));
    }

    @Override
    public RoleResponse add(RoleRequest role) {
        if (role == null)
            throw new BadRequestException(config.getString("role.is_null"));

        ErrorMessageData emd = new ErrorMessageData(Response.Status.BAD_REQUEST
                .getStatusCode());

        if (TextUtil.isEmpty(role.getName()))
            emd.addMessage(config.getString("role.name"));
        if (roleController.findByName(role.getName()) != null)
            emd.addMessage(config.getString("role.n_exists"));
        if (role.getPermissions() == null || role.getPermissions().isEmpty())
            emd.addMessage(config.getString("role.permissions"));

        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);

        for (PermissionData permission : role.getPermissions()) {
            if (permission.getFunctionality() == null
                    || permission.getPermissionType() == null)
                throw new BadRequestException(config
                        .getString("role.functionality"));
        }

        return convertToDto(roleController.save(convertToEntity(role)));
    }

    @Override
    public RoleResponse update(Long id, RoleRequest role) {
        if (role == null)
            throw new BadRequestException(config.getString("role.is_null"));

        Role currentRole = getEntityRole(id);

        if (!TextUtil.isEmpty(role.getName())
                && !currentRole.getName().equalsIgnoreCase(role.getName())) {
            if (roleController.findByName(role.getName()) != null)
                throw new BadRequestException(config.getString("role.n_exists"));
            currentRole.setName(role.getName());
        }

        permissionController.deleteAllPermissionByRoleId(currentRole.getId());
        currentRole.setPermissions(convertToEntity(role).getPermissions());

        for (PermissionData permission : role.getPermissions()) {
            if (permission.getFunctionality() == null
                    || permission.getPermissionType() == null)
                throw new BadRequestException(config
                        .getString("role.functionality"));
        }

        return convertToDto(roleController.save(currentRole));
    }

    @Override
    public RoleResponse delete(Long id) {
        RoleResponse currentRole = getRole(id);
        roleController.delete(id);
        return currentRole;
    }

    @Override
    public Role convertToEntity(RoleRequest d) {
        List<Permission> permissions = null;
        if (d.getPermissions() != null && !d.getPermissions().isEmpty()) {
            permissions = new ArrayList<>();
            for (PermissionData p : d.getPermissions()) {
                permissions.add(new Permission(p.getFunctionality(),
                        p.getPermissionType()));
            }
        }
        return new Role(d.getName(), permissions);
    }

    @Override
    public RoleResponse convertToDto(Role e) {
        List<PermissionData> permissions = null;
        if (e.getPermissions() != null && !e.getPermissions().isEmpty()) {
            permissions = new ArrayList<>();
            for (Permission p : e.getPermissions()) {
                permissions.add(new PermissionData(p.getFunctionality(),
                        p.getPermissionType()));
            }
        }
        return new RoleResponse(e.getId(), e.getName(), permissions);
    }

}
