package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Address;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface AddressDao extends DataAccessObject<Address, Long> {

    /**
     * Returns a list of addresses with the person ID provided
     *
     * @param personId Owner ID
     * @return List of addresses
     */
    List<Address> getAllByPersonId(Long personId);

    /**
     * Returns a list of addresses with the establishment ID provided
     *
     * @param establishmentId Owner ID
     * @return List of addresses
     */
    List<Address> getAllByEstablishmentId(Long establishmentId);

    /**
     * Returns a specific address with ID and the person ID provided
     *
     * @param id Address ID
     * @param personId ID of the owner
     * @return Searched address
     */
    Address getByPersonId(Long id, Long personId);

    /**
     * Returns a specific address with ID and the establishment ID provided
     *
     * @param id Address ID
     * @param establishmentId Owner ID
     * @return Searched address
     */
    Address getByEstablishmentId(Long id, Long establishmentId);
}
