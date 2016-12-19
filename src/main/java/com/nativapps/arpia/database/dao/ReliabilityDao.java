package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Reliability;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.0
 */
public interface ReliabilityDao extends DataAccessObject<Reliability, Long> {

    /**
     * Get reliability information from the given messenger ID.
     *
     * @param messengerId messenger ID
     * @return reliability data
     */
    Reliability findByMessengerId(Long messengerId);

}
