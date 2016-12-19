package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.MessengerActionRequest;
import com.nativapps.arpia.model.dto.MessengerActionResponse;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public interface MessengerActionService extends Service {

    /**
     * Create a new action with messenger ID.
     *
     * @param messengerId the messenger ID
     * @param action the action data
     * @return the action saved
     */
    public MessengerActionResponse add(Long messengerId,
            MessengerActionRequest action);

    /**
     * Get a messenger action by id.
     *
     * @param id the action ID
     * @return the messenger action
     */
    public MessengerActionResponse get(Long id);

    /**
     * Get all messenger action by the given messenger ID.
     *
     * @param messengerId the messenger ID
     * @param start first action to get
     * @param size size of list
     * @return list of actions
     */
    public List<MessengerActionResponse> getAll(Long messengerId, int start, int size);

    /**
     * Get all messenger actions.
     *
     * @param start first action to get
     * @param size size of list
     * @return list of actions
     */
    public List<MessengerActionResponse> getAll();

    /**
     * Update a action from the given ID.
     *
     * @param id the action ID
     * @param action the action data
     * @return the action updated
     */
    public MessengerActionResponse update(Long id,
            MessengerActionRequest action);

    /**
     * Delete a action from the given ID.
     *
     * @param id the action ID
     * @return the action deleted
     */
    public MessengerActionResponse delete(Long id);

}
