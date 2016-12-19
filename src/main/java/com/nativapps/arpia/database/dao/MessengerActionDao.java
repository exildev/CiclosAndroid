package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.MessengerAction;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface MessengerActionDao extends DataAccessObject<MessengerAction, Long> {

    /**
     * Return a messenger action list by messenger id
     *
     * @param messengerId the messenger ID
     * @return the messenger action data
     */
    public List<MessengerAction> findAllByMessengerId(Long messengerId);

    /**
     * Return a filter messenger action list by string
     *
     * @param start Initial index
     * @param size List size
     * @param messengerId messenger id
     * @return Filter list
     */
    public List<MessengerAction> findAll(int start, int size, Long messengerId);

}
