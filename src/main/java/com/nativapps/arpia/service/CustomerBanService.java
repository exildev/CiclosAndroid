package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.CustomerBanHistoryRequest;
import com.nativapps.arpia.model.dto.CustomerBanHistoryResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface CustomerBanService extends Service {
    
    /**
     * Returns the customer ban histories
     * 
     * @param start Initial index
     * @param size List size
     * @param customerId Customer ID
     * @return Customer ban history list
     */
    List<CustomerBanHistoryResponse> getAll(int start, int size, Long customerId);
    
    /**
     * Return the last customer ban history
     * 
     * @param customerId Customer ID
     * @return Last customer ban history
     */
    CustomerBanHistoryResponse getLastBan(Long customerId);
    
    /**
     * Add a new customer ban to the history
     * 
     * @param customerId Customer ID
     * @param data Customer ban information
     * @return Added customer ban history
     */
    CustomerBanHistoryResponse ban(Long customerId, CustomerBanHistoryRequest data);
    
    /**
     * Cancel the last customer ban history
     * 
     * @param customerId Customer ID
     * @return Canceled customer ban history
     */
    CustomerBanHistoryResponse cancelBan(Long customerId);
}
