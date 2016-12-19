package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Email;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface EmailDao extends DataAccessObject<Email, Long> {

    /**
     * Returns a list of emails with the person ID provided
     *
     * @param personId Owner id
     * @return List of emails
     */
    List<Email> getAllByPersonId(Long personId);

    /**
     * Check if an email address exists in the database
     *
     * @param address email address
     * @return true if the email address exists in the database, otherwise false
     */
    boolean exists(String address);
}
