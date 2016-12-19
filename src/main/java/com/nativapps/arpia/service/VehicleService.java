package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.VehicleRequest;
import com.nativapps.arpia.model.dto.VehicleResponse;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface VehicleService extends Service {

    /**
     * Create new vehicle from the given data
     *
     * @param messengerId Messenger id
     * @param data Vehicle information
     * @return Added vehicle
     */
    public VehicleResponse add(Long messengerId, VehicleRequest data);

    /**
     * Get vehicle information by vehicle id provided.
     *
     * @param id vehicle id
     * @return found vehicle.
     */
    public VehicleResponse get(Long id);

    /**
     * Get all vehicle by messenger id provided
     *
     * @param messengerId
     * @return
     */
    public List<VehicleResponse> getAll(Long messengerId);

    /**
     * Gets all vehicles by pagination.
     *
     * @param start
     * @param size
     * @return vehicle list
     */
    public List<VehicleResponse> getAll(int start, int size);

    /**
     * Updates the vehicle information by vehicle id provided.
     *
     * @param id
     * @param data
     * @return updated vehicle
     */
    public VehicleResponse update(Long id, VehicleRequest data);

    /**
     * Removes the vehicle information by vehicle id provided.
     *
     * @param id
     * @return deleted vehicle
     */
    public VehicleResponse delete(Long id);
}
