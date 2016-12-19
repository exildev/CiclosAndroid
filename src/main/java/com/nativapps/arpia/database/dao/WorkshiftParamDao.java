package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.WorkshiftParam;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1
 */
public interface WorkshiftParamDao extends DataAccessObject<WorkshiftParam, Long> {

    /**
     * Get a workshift param from the given messenger ID.
     *
     * @param messengerId messenger ID
     * @return workshift param information
     */
    WorkshiftParam findByMessengerId(Long messengerId);

}
