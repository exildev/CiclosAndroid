package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.MobileNumberRequest;
import com.nativapps.arpia.model.dto.MobileNumberResponse;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface MobileNumberService extends Service {

    /**
     * Create new mobile number from the given data.
     *
     * @param quantity quantity to create
     * @param operationId operation id to which the mobiles will be assigned.
     * @return mobile number saved
     */
    public List<MobileNumberResponse> add(Integer quantity,
            Long operationId);

    /**
     * Get a mobile number information from the given ID.
     *
     * @param id mobile number id
     * @return number mobilemobile number searched
     */
    public MobileNumberResponse get(Long id);

    /**
     * Gets all mobiles numbers.
     *
     * @param start initial number the pagination
     * @param size size number the pagination
     * @return paginated list of mobiles numbers
     */
    public List<MobileNumberResponse> getAll(int start, int size);

    /**
     * Updates the corresponding mobile number.
     *
     * @param id mobile number ID
     * @param data mobile number data
     * @return mobile number updated.
     */
    public MobileNumberResponse update(Long id, MobileNumberRequest data);

    /**
     * Remove the mobile number information provided by the ID.
     *
     * @param id mobile number ID
     * @return mobile number deleted
     */
    public MobileNumberResponse delete(Long id);

}
