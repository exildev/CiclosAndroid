package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.ReliabilityRequest;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class ReliabilityValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private ReliabilityValidator() {
    }

    /**
     * Evaluate if the reliability object contains errors or missing
     * requeriments to meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param reliability reliability to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateReliability(ReliabilityRequest reliability,
            ErrorMessageData emd, ConfigLanguage config) {
        missingParameters(config, emd);

        if (reliability.getConfidence() == null)
            emd.addMessage(config.getString("reliability.confidence"));
        else if (reliability.getConfidence() < 0 && reliability.getConfidence() > 4)
            emd.addMessage(config.getString("reliability.confidence_valid"));

        if (reliability.getDailySaving() == null && reliability.getDailySaving() < 0)
            emd.addMessage(config.getString("reliability.daily_saving"));
        if (reliability.getMinNumberBase() == null && reliability.getMinNumberBase() < 0)
            emd.addMessage(config.getString("reliability.minimum_base"));
        if (reliability.getMinProduction() == null && reliability.getMinProduction() < 0)
            emd.addMessage(config.getString("reliability.minimum_production"));
        if (reliability.getMinService() == null && reliability.getMinService() < 0)
            emd.addMessage(config.getString("reliability.minimum_service"));
        if (reliability.getManagement() == null)
            emd.addMessage(config.getString("reliability.management"));
        if (reliability.getSpeed() == null)
            emd.addMessage(config.getString("reliability.speed"));
    }
}
