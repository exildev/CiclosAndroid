package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.SimpleMessengerResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface MessengerBlackListService extends Service {

    /**
     * Returns a messenger black list
     *
     * @param id Customer ID
     * @return Messenger blacklist
     */
    List<SimpleMessengerResponse> getAll(Long id);

    /**
     * Returns a messenger in the blacklist by index
     *
     * @param id Customer ID
     * @param index Index in the blacklist
     * @return Searched messenger
     */
    SimpleMessengerResponse get(Long id, Integer index);

    /**
     * Add a new messenger in the blacklist
     *
     * @param id Customer ID
     * @param messengerId Messenger ID
     * @return Added messenger
     */
    SimpleMessengerResponse add(Long id, Long messengerId);

    /**
     * Delete a messenger in the blacklist
     *
     * @param id Customer ID
     * @param index Index in blacklist
     * @return Deleted messenger
     */
    SimpleMessengerResponse delete(Long id, Integer index);
}
