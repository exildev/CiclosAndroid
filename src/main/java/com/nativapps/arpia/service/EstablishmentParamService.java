package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.EstablishmentParamRequest;
import com.nativapps.arpia.model.dto.EstablishmentParamResponse;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface EstablishmentParamService extends Service {

    /**
     * Returns a establishment parameters information from id provided
     *
     * @param id Establishment id
     * @return Searched establishment parameter information
     */
    EstablishmentParamResponse get(Long id);

    /**
     * Update a establishment parameters information
     *
     * @param id Establishment id
     * @param data Establishment parameter information
     * @return Updated establishment parameters information
     */
    EstablishmentParamResponse update(Long id,
            EstablishmentParamRequest data);

}
