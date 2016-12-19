package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.InventoryRequest;
import com.nativapps.arpia.model.dto.InventoryResponse;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface InventoryService extends Service {

    /**
     * Create new inventory from the given data
     *
     * @param data information of inventory entity
     * @return saved entity
     */
    InventoryResponse add(InventoryRequest data);

    /**
     * Get inventory information by id provided
     *
     * @param id entity identifier to search
     * @return searched entity
     */
    InventoryResponse get(Long id);

    /**
     * Get all inventory
     *
     * @return list of inventory entities
     */
    List<InventoryResponse> getAll();

    /**
     * Update information of inventory by id provided, you can only change the
     * element name.
     *
     * @param id entity identifier to update
     * @param data information of inventory entity
     * @return saved entity
     */
    InventoryResponse update(Long id, InventoryRequest data);

    /**
     * Delete inventory entity by id provided
     *
     * @param id entity identifier to delete
     * @return deleted entity
     */
    InventoryResponse delete(Long id);
}
