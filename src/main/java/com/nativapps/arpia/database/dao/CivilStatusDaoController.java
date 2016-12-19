package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.CivilStatus;

/**
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class CivilStatusDaoController extends EntityDao<CivilStatus, Long>
        implements CivilStatusDao {

    public CivilStatusDaoController() {
        super(CivilStatus.class);
    }

}
