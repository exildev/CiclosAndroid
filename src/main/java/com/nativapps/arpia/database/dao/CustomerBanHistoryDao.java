package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.CustomerBanHistory;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface CustomerBanHistoryDao
        extends DataAccessObject<CustomerBanHistory, Long> {

    /**
     * Returns a list with customer ban histories
     * 
     * @param start Initial index
     * @param size List size
     * @param customerId Customer ID
     * @return Customer ban history list
     */
    List<CustomerBanHistory> findAllByCustomerId(int start, int size, 
            Long customerId);
    
    /**
     * Returns de the last customer ban history by provided ID
     * 
     * @param customerId Customer ID
     * @return Last customer ban history
     */
    CustomerBanHistory findLastBanByCustomerId(Long customerId);
}
