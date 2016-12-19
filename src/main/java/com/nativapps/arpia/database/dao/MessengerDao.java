package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Messenger;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface MessengerDao extends DataAccessObject<Messenger, Long> {

    /**
     * Return a filter Messenger list by string
     *
     * @param start Initial index
     * @param size List size
     * @return Filter list
     */
    public List<Messenger> findAll(int start, int size);
}
