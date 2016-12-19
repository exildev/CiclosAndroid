package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.WorkshiftParamRequest;
import com.nativapps.arpia.model.dto.WorkshiftParamResponse;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0.1
 */
public interface WorkshiftParamService extends Service {

    /**
     * Get the work shift corresponding the messenger.
     *
     * @param messengerId
     * @return
     */
    public WorkshiftParamResponse get(Long messengerId);

    /**
     * Updates the corresponding work shift the messenger.
     *
     * @param messengerId
     * @param data
     * @return updated list of faults.
     */
    public WorkshiftParamResponse update(Long messengerId, WorkshiftParamRequest data);

}
