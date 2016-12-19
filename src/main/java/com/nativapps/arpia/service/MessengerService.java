package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.MessengerRequest;
import com.nativapps.arpia.model.dto.MessengerResponse;
import java.util.List;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface MessengerService extends Service {

    /**
     * Create new Messenger from the given data.
     * <p>
     * In this method you can add references, addresses, emails and phones.
     *
     * @param dataRequest information of Messenger entity
     * @return saved entity
     */
    MessengerResponse add(MessengerRequest dataRequest);

    /**
     * Get Messenger information by id provided
     *
     * @param id entity identifier to search
     * @return searched entity
     */
    MessengerResponse get(Long id);

    /**
     * Get all Messenger
     *
     * @param start first element to get
     * @param size size of list
     * @return list of Messenger entities
     */
    List<MessengerResponse> getAll(int start, int size);

    /**
     * Update information of Messenger by id provided.
     * <p>
     * The references, addresses, emails and phones are updated from your
     * service.
     *
     * @param id entity identifier to update
     * @param dataRequest information of Messenger entity
     * @return saved entity
     */
    MessengerResponse update(Long id, MessengerRequest dataRequest);

    /**
     * Delete Messenger entity by id provided
     *
     * @param id entity identifier to delete
     * @return deleted entity
     */
    MessengerResponse delete(Long id);

}
