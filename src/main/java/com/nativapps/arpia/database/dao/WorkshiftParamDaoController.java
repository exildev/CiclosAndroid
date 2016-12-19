package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.WorkshiftParam;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1
 */
public class WorkshiftParamDaoController extends EntityDao<WorkshiftParam, Long>
        implements WorkshiftParamDao {

    private static final WorkshiftParamDaoController INSTANCE = new WorkshiftParamDaoController();

    private WorkshiftParamDaoController() {
        super(WorkshiftParam.class);
    }

    public static WorkshiftParamDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public WorkshiftParam findByMessengerId(Long messengerId) {
        return executeNamedQuery("workshiftParam.findByMessengerId",
                new Parameter("id", messengerId));
    }

}
