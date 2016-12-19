package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.FaultRequest;
import com.nativapps.arpia.model.dto.FaultResponse;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public interface FaultService extends Service {

    /**
     * Create new fault de messenger from the given data.
     *
     * @param messengerId
     * @param data
     * @return
     */
    public FaultResponse add(Long messengerId, FaultRequest data);

    /**
     * Get the fault corresponding the messenger.
     *
     * @param index
     * @param messengerId
     * @return
     */
    public FaultResponse get(Integer index, Long messengerId);

    /**
     * Get all faults corresponding the messenger.
     *
     * @param messengerId
     * @return
     */
    public List<FaultResponse> getAll(Long messengerId);

    /**
     * Updates the corresponding fault the messenger.
     *
     * @param index
     * @param messengerId
     * @param data
     * @return updated list of faults.
     */
    public FaultResponse update(Integer index, Long messengerId,
            FaultRequest data);

    /**
     * eliminates the corresponding fault the id of a messenger.
     *
     * @param index
     * @param messengerId
     * @return the fault removed.
     */
    public FaultResponse delete(Integer index, Long messengerId);
}
