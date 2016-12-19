package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.BonusRequest;
import com.nativapps.arpia.model.dto.BonusResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface BonusService extends Service {

    /**
     * Returns the bonus list associated to the client ID
     *
     * @param start Initial index
     * @param size List size
     * @param customerId Customer ID to search
     * @return Bonus list
     */
    List<BonusResponse> getAll(int start, int size, Long customerId);

    /**
     * Returns a specific bonus by client ID and its own id
     *
     * @param customerId Customer ID to search
     * @param bonusId Bonus identifier to search
     * @return Bonus entity
     */
    BonusResponse get(Long customerId, Long bonusId);

    /**
     * Add a bonus to client by client ID
     *
     * @param customerId Customer ID to search
     * @param authToken Authorization token
     * @param bonus Bonus information
     * @return Added bonus
     */
    BonusResponse add(Long customerId, String authToken, BonusRequest bonus);

    /**
     * Update a bonus information
     *
     * @param customerId Customer ID to search
     * @param bonusId Bonus identifier to search
     * @param authToken Authorization token
     * @param bonus Bonus information
     * @return Updated bonus
     */
    BonusResponse update(Long customerId, Long bonusId, String authToken, 
            BonusRequest bonus);

    /**
     * Delete a bonus from the list
     * 
     * @param customerId Customer ID to search
     * @param bonusId Bonus identifier to search
     * @return Deleted bonus
     */
    BonusResponse delete(Long customerId, Long bonusId);
}
