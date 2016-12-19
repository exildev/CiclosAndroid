package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.VehicleRequest;
import com.nativapps.arpia.util.TextUtil;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class VehicleValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private VehicleValidator() {
    }

    /**
     * Validate if the data has the required structure.
     *
     * @param data vehicle information
     * @param errors error message data
     * @param config language configuration
     */
    public static void evaluateVehicle(VehicleRequest data,
            ErrorMessageData errors, ConfigLanguage config) {
        missingParameters(config, errors);

        if (TextUtil.isEmpty(data.getColor()))
            errors.addMessage(
                    config.getString("vehicle.color"));
        if (TextUtil.isEmpty(data.getLicensePlate()))
            errors.addMessage(
                    config.getString("vehicle.license_plate"));
        if (TextUtil.isEmpty(data.getModel()))
            errors.addMessage(
                    config.getString("vehicle.model"));
        if (TextUtil.isEmpty(data.getTrademark()))
            errors.addMessage(
                    config.getString("vehicle.trademark"));
        if (data.getOwnership() == null)
            errors.addMessage(
                    config.getString("vehicle.ownership"));
        if (data.getVehicleCondition() == null)
            errors.addMessage(
                    config.getString("vehicle.condition"));
        if (data.getTecnomecanicaExpiration() == null)
            errors.addMessage(
                    config.getString("vehicle.technical_mechanics"));
        if (data.getSoatExpiration() == null)
            errors.addMessage(
                    config.getString("vehicle.soat_expiration"));
    }

}
