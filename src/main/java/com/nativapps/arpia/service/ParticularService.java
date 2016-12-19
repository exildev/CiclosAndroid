package com.nativapps.arpia.service;

import com.nativapps.arpia.database.entity.Gender;
import com.nativapps.arpia.model.OrderType;
import com.nativapps.arpia.model.dto.ParticularRequest;
import com.nativapps.arpia.model.dto.ParticularResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface ParticularService extends Service {

    /**
     * Create new Particular client from the given data
     *
     * @param data Particular entity information
     * @return saved entity
     */
    ParticularResponse add(ParticularRequest data);

    /**
     * Get Particular client information by id provided
     *
     * @param id entity identifier to search
     * @return searched entity
     */
    ParticularResponse get(Long id);

    /**
     * Get all Particular clients
     *
     * @param start initial index
     * @param size size of list
     * @param search String to search
     * @param orderBy Atributte to order
     * @param orderType Order type to list
     * @param gender Filter gender
     * @return Particular entities list
     */
    List<ParticularResponse> getAll(int start, int size, String search, 
            String orderBy, OrderType orderType, Gender gender);

    /**
     * Update information of Particular client by id provided
     *
     * @param id entity identifier to update
     * @param data Particular entity information
     * @return saved entity
     */
    ParticularResponse update(Long id, ParticularRequest data);

    /**
     * Delete Particular client entity by id provided
     *
     * @param id entity identifier to delete
     * @return deleted entity
     */
    ParticularResponse delete(Long id);

}
