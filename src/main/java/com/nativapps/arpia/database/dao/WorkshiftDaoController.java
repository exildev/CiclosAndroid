package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Workshift;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class WorkshiftDaoController extends EntityDao<Workshift, Long>
        implements WorkshiftDao {

    private static final WorkshiftDaoController INSTANCE = new WorkshiftDaoController();

    private WorkshiftDaoController() {
        super(Workshift.class);
    }

    public static WorkshiftDaoController getInstance() {
        return INSTANCE;
    }
}
