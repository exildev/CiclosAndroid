package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Phone;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface PhoneDao extends DataAccessObject<Phone, Long> {

    /**
     * Returns a list of phones with the ID provided
     *
     * @param personId Owner ID
     * @return List of phones
     */
    List<Phone> getAllByPersonId(Long personId);

    /**
     * Check if a phone number exists in the database
     *
     * @param phone phone number
     * @return true if the phone number exists in the database, otherwise false
     */
    boolean exists(String phone);

}
