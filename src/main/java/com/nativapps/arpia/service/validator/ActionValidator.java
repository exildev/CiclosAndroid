package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.MessengerActionRequest;
import com.nativapps.arpia.util.TextUtil;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class ActionValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private ActionValidator() { }

    /**
     * Evaluate if the messenger action object contains errors or missing
     * requeriments to meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param action the messenger action to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateAction(MessengerActionRequest action,
            ErrorMessageData emd, ConfigLanguage config) {
        missingParameters(config, emd);

        if (TextUtil.isEmpty(action.getTitle()))
            emd.addMessage(config.getString("m_action.title"));
        if (TextUtil.isEmpty(action.getDescription()))
            emd.addMessage(config.getString("m_action.descripcion"));
    }

}
