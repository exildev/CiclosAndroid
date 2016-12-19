package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Role;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class RoleDaoController extends EntityDao<Role, Long>
        implements RoleDao {

    private static final RoleDaoController INSTANCE = new RoleDaoController();

    private RoleDaoController() {
        super(Role.class);
    }

    public static RoleDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Role findByName(String name) {
        return executeNamedQuery("role.findByName", new Parameter("name", name));
    }

}
