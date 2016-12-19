package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.ReferenceRequest;
import com.nativapps.arpia.model.dto.ReferenceResponse;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface ReferenceService extends Service {

    /**
     * Create new reference from the given data
     *
     * @param messengerId
     * @param data
     * @return
     */
    ReferenceResponse add(Long messengerId, ReferenceRequest data);

    /**
     * Get reference information by index provided of messenger.
     *
     * @param index
     * @param messengerId
     * @return
     */
    ReferenceResponse get(Integer index, Long messengerId);

    /**
     * Get all reference by person id provided
     *
     * @param personId
     * @return
     */
    List<ReferenceResponse> getAll(Long personId);

    /**
     * Update the reference information provided by the index and corresponding
     * to a messenger.
     *
     * @param index
     * @param messengerId
     * @param data
     * @return
     */
    ReferenceResponse update(Integer index, Long messengerId,
            ReferenceRequest data);

    /**
     * Remove the reference information provided by the index and corresponding
     * to a messenger.
     *
     * @param id
     * @param messengerId
     * @return
     */
    ReferenceResponse delete(Integer id, Long messengerId);
}
