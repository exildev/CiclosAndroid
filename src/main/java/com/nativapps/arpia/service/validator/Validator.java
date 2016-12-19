package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    protected Validator() { }

    /**
     * Verify if the methods in this class have the parameters completed.
     *
     * @param config language configuration
     * @param emd error message data
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    protected static void missingParameters(ConfigLanguage config,
            ErrorMessageData emd) throws IllegalArgumentException {
        if (config == null)
            throw new IllegalArgumentException("The language configuration is "
                    + "required.");
        if (emd == null)
            throw new IllegalArgumentException("The error message data is "
                    + "required for add message if email don't meet with all "
                    + "evaluations.");
    }

}
