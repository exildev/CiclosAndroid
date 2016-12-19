package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface AddressService extends Service {

    enum Owner {
        PERSON, ESTABLISHMENT
    }

    /**
     * Create new Address from the given data
     *
     * @param id ID of the owner
     * @param type Owner type
     * @param data information of address
     * @return saved address
     */
    AddressResponse add(Long id, Owner type, AddressRequest data);

    /**
     * Get address information by person id provided
     *
     * @param id ID of the owner
     * @param type Owner type
     * @return searched address
     */
    List<AddressResponse> getAll(Long id, Owner type);

    /**
     * Get address information by person id provided
     *
     * @param id ID of the address entity
     * @param onwerId ID of the owner
     * @param type Owner type
     * @return searched address
     */
    AddressResponse get(Long id, Long onwerId, Owner type);

    /**
     * Update address information by id provided
     *
     * @param id ID of the address
     * @param ownerId Id of the owner
     * @param type Onwer type
     * @param data Address information to update
     * @return saved address
     */
    AddressResponse update(Long id, Long ownerId, Owner type,
            AddressRequest data);

    /**
     * Delete address by id provided
     *
     * @param id ID of the address
     * @param ownerId ID of the person
     * @param type Onwer type
     * @return saved address
     */
    AddressResponse delete(Long id, Long ownerId, Owner type);
}
