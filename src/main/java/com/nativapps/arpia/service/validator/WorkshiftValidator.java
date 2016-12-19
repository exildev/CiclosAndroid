package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.WorkshiftRequest;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class WorkshiftValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private WorkshiftValidator() { }

    /**
     * Evaluate if the workshift object contains errors or missing requeriments
     * to meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param workshift workshift to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateWorkshift(WorkshiftRequest workshift,
            ErrorMessageData emd, ConfigLanguage config) {
        missingParameters(config, emd);

        if (workshift.getEnterTime() == null)
            emd.addMessage(config.getString("workshift.enter_time"));
        if (workshift.getExitTime() == null)
            emd.addMessage(config.getString("workshift.exit_time"));
    }

}
