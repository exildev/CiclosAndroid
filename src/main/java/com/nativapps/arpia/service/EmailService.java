package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.EmailResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface EmailService extends Service{

    /**
     * Create a new email to a person from the data provided
     *
     * @param personId ID of the person
     * @param data information email
     * @return saved email
     */
    EmailResponse add(Long personId, EmailRequest data);

    /**
     * Get email information by id provided
     *
     * @param index email index to search
     * @param personId ID of the person
     * @return searched email
     */
    EmailResponse get(Integer index, Long personId);

    /**
     * Get all email by person id
     *
     * @param personId ID of the person
     * @return list of emails
     */
    List<EmailResponse> getAllByPersonId(Long personId);

    /**
     * Delete email by id provided
     *
     * @param index email index to delete
     * @param personId ID of the person
     * @return deleted email
     */
    EmailResponse delete(Integer index, Long personId);
}
