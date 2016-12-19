package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.ParticularParamRequest;
import com.nativapps.arpia.model.dto.ParticularParamResponse;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface ParticularParamService extends Service {

    /**
     * Returns a particular parameters information from id provided
     *
     * @param id Particular id
     * @return Searched particular parameter information
     */
    ParticularParamResponse get(Long id);

    /**
     * Update a particular parameters information
     *
     * @param id Particular id
     * @param data Particular parameter information
     * @return Updated particular parameters information
     */
    ParticularParamResponse update(Long id,
            ParticularParamRequest data);
}
