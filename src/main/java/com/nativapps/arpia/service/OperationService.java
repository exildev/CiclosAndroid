package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.OperationRequest;
import com.nativapps.arpia.model.dto.OperationResponse;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface OperationService extends Service {

    /**
     * Get a list with all operations.
     *
     * @return operations list.
     */
    List<OperationResponse> getAll();

    /**
     * Get a operation information from the given ID.
     *
     * @param id operation ID.
     * @return operation information.
     */
    OperationResponse get(Long id);

    /**
     * Get a operation information from the given name.
     *
     * @param name the name.
     * @return operation information.
     */
    OperationResponse getOperationByName(String name);

    /**
     * Create a new operation from the information provided.
     *
     * @param operation operation information.
     * @return operation created.
     */
    OperationResponse add(OperationRequest operation);

    /**
     * Update the operation data from the information provided.
     *
     * @param id operation ID.
     * @param operation operation information.
     *
     * @return operation updated.
     */
    OperationResponse update(Long id, OperationRequest operation);

    /**
     * Delete a operation information from the given ID.
     *
     * @param id operation ID.
     * @return the information of operation deleted.
     */
    OperationResponse delete(Long id);
}
