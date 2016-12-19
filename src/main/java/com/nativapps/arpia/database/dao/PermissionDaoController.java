package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Permission;
import com.nativapps.arpia.database.exception.DatabaseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class PermissionDaoController extends EntityDao<Permission, Long>
        implements PermissionDao {

    private static final Logger LOG = Logger
            .getLogger(PermissionDaoController.class.getName());

    private static final PermissionDaoController INSTANCE
            = new PermissionDaoController();

    private PermissionDaoController() {
        super(Permission.class);
    }

    public static PermissionDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Permission> findAllPermissionsByRoleId(long roleId) {
        return executeNamedQueryForList("permission.findByRoleId",
                new Parameter("roleId", roleId));
    }

    @Override
    public void deleteAllPermissionByRoleId(long roleId) {
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();

                Query deleteQuery = entityManager
                        .createNamedQuery("permission.deleteByRoleId");
                deleteQuery.setParameter("roleId", roleId);
                deleteQuery.executeUpdate();

                transaction.commit();
            }
        } catch (Exception ex) {
            String message = "Error deleting all permissions by role ID.";
            LOG.log(Level.SEVERE, message, ex);
            throw new DatabaseException(message, ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

}
