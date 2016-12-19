package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.CustomerDataDto;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface CustomerDataService extends Service {

    /**
     * Returns customer data list
     * 
     * @param start Initial indez
     * @param size List size
     * @return Customer data list
     */
    List<CustomerDataDto> getAll(int start, int size);
    
    /**
     * Return a specific customer data by ID
     * 
     * @param id Customed ID
     * @return Return customer data entity
     */
    CustomerDataDto get(Long id);
}
