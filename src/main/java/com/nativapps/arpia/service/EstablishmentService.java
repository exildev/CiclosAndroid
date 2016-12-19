package com.nativapps.arpia.service;

import com.nativapps.arpia.model.OrderType;
import com.nativapps.arpia.model.dto.EstablishmentRequest;
import com.nativapps.arpia.model.dto.EstablishmentResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface EstablishmentService extends Service{

    /**
     * Create new Establishment client from the given data
     *
     * @param data information of Establishment entity
     * @return saved entity
     */
    EstablishmentResponse add(EstablishmentRequest data);

    /**
     * Get Establishment client information by id provided
     *
     * @param id entity identifier to search
     * @return searched entity
     */
    EstablishmentResponse get(Long id);

    /**
     * Get all Establishment clients
     *
     * @param start Initial index
     * @param size List size
     * @param search Filter particular list
     * @param orderBy Atributte to order
     * @param orderType Order type to list
     * @return list of Establishment entities
     */
    List<EstablishmentResponse> getAll(int start, int size, String search, 
            String orderBy, OrderType orderType);

    /**
     * Update information of Establishment client by id provided
     *
     * @param id entity identifier to update
     * @param data information of Establishment entity
     * @return saved entity
     */
    EstablishmentResponse update(Long id, EstablishmentRequest data);

    /**
     * Establishment Particular client entity by id provided
     *
     * @param id entity identifier to delete
     * @return deleted entity
     */
    EstablishmentResponse delete(Long id);
}
