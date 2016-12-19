package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Disease;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class DiseaseDaoController extends EntityDao<Disease, Long>
        implements DiseaseDao {

    private static final DiseaseDaoController INSTANCE
            = new DiseaseDaoController();

    private DiseaseDaoController() {
        super(Disease.class);
    }

    public static DiseaseDaoController getInstance() {
        return INSTANCE;
    }

}
