package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.AdministratorRequest;
import com.nativapps.arpia.model.dto.AdministratorResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface AdministratorService extends Service {

    /**
     * Create a new establishment's administrator client from the given data
     *
     * @param establishmentId establishment's ID
     * @param data information of establishment's administrator entity
     * @return saved entity
     */
    AdministratorResponse add(Long establishmentId,
            AdministratorRequest data);

    /**
     * Get establishment's administrator client information by id provided
     *
     * @param establishmentId establishment's ID
     * @param id entity identifier to search
     * @return searched entity
     */
    AdministratorResponse get(Long establishmentId, Long id);

    /**
     * Get all establishment's administrator clients
     *
     * @param establishmentId establishment's ID
     * @return list of establishment's administrator entities
     */
    List<AdministratorResponse> getAll(Long establishmentId);

    /**
     * Update information of establishment's administrator client by id provided
     *
     * @param establishmentId establishment's ID
     * @param id entity identifier to update
     * @param data information of establishment's administrator entity
     * @return saved entity
     */
    AdministratorResponse update(Long establishmentId, Long id,
            AdministratorRequest data);

    /**
     * Delete establishment's administrator client entity by id provided
     *
     * @param establishmentId establishment's ID
     * @param id entity identifier to delete
     * @return deleted entity
     */
    AdministratorResponse delete(Long establishmentId, Long id);
}
