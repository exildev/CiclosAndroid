package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.ReliabilityRequest;
import com.nativapps.arpia.model.dto.ReliabilityResponse;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public interface ReliabilityService extends Service {

    /**
     * Get the reliability corresponding the messenger.
     *
     * @param messengerId messenger ID
     * @return reliability data
     */
    public ReliabilityResponse get(Long messengerId);

    /**
     * Updates the corresponding reliability the messenger.
     *
     * @param messengerId messenger ID
     * @param reliability reliability data
     * @return reliability data updated
     */
    public ReliabilityResponse update(Long messengerId,
            ReliabilityRequest reliability);

}
