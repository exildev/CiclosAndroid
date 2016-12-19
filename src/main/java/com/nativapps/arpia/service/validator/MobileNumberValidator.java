package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.MobileNumberRequest;
import com.nativapps.arpia.service.exception.ServiceException;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MobileNumberValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private MobileNumberValidator() {
    }

    /**
     * Evaluate if the mobile number object contains errors or missing
     * requeriments to meet.
     *
     * @param mobileNumber mobile number to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateMobileNumber(MobileNumberRequest mobileNumber,
            ErrorMessageData emd, ConfigLanguage config)
            throws BadRequestException, ServiceException {
        missingParameters(config, emd);

        if (mobileNumber.getNumber() != null
                || mobileNumber.getNumber() > 0)
            emd.addMessage(
                    config.getString("mobile_number.number_is_required"));
        if (mobileNumber.getOperationId() == null
                || mobileNumber.getOperationId() > 0)
            emd.addMessage(
                    config.getString("mobile_number.operation_is_required"));
    }
}
