package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Operation;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.1.0
 */
public interface OperationDao extends DataAccessObject<Operation, Long> {

    /**
     * Search a operation from the given name.
     *
     * @param name the name.
     * @return operation information.
     */
    Operation findByName(String name);

    /**
     * Search a operation from the given alias.
     *
     * @param alias the alias.
     * @return operation information.
     */
    Operation findByAlias(String alias);

}
