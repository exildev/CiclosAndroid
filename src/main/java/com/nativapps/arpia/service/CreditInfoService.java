package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.CreditInfoDataRequest;
import com.nativapps.arpia.model.dto.CreditInfoDataResponse;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface CreditInfoService extends Service {
    
    /**
     * Returns a credit information by customer ID
     * 
     * @param id Customer ID
     * @return Credit Information
     */
    CreditInfoDataResponse get(Long id);
    
    /**
     * Updates a credit information by customer ID
     * 
     * @param customerId customer ID
     * @param data Credit information
     * @return Updated credit information
     */
    CreditInfoDataResponse update(Long customerId, CreditInfoDataRequest data);
}
