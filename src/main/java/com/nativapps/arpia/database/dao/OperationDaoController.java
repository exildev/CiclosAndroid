package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Operation;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.1.0
 */
public class OperationDaoController extends EntityDao<Operation, Long>
        implements OperationDao {

    private static final OperationDaoController INSTANCE
            = new OperationDaoController();

    private OperationDaoController() {
        super(Operation.class);
    }

    public static OperationDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Operation findByName(String name) {
        return executeNamedQuery("operation.findByName",
                new Parameter("name", name));
    }

    @Override
    public Operation findByAlias(String alias) {
        return executeNamedQuery("operation.findByAlias",
                new Parameter("alias", alias));
    }

}
