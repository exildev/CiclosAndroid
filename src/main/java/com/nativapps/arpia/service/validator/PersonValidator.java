package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.EmailDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.dao.PhoneDao;
import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.util.TextUtil;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class PersonValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private PersonValidator() { }

    /**
     * Evaluate if the address object contains errors or missing requeriments to
     * meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param address address to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateAddress(AddressRequest address,
            ErrorMessageData emd, ConfigLanguage config)
            throws IllegalArgumentException {
        missingParameters(config, emd);

        if (address.getNeighborhood() == null)
            emd.addMessage(config.getString("address.neighborhood_null"));

        NeighborhoodDao controller = EntityControllerFactory.getNeighborhoodController();
        if (controller.find(address.getNeighborhood()) == null)
            emd.addMessage(config.getString("address.neighborhood_not_found"));

        if (TextUtil.isEmpty(address.getResidentialAddress()))
            emd.addMessage(config.getString("address.resid_address_null"));
        if (TextUtil.isEmpty(address.getTag()))
            emd.addMessage(config.getString("address.tag_null"));
    }

    /**
     * Evaluate if the email object contains errors or missing requeriments to
     * meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param email email to evaluate
     * @param emd error message data object
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateEmail(EmailRequest email, ErrorMessageData emd,
            ConfigLanguage config) throws IllegalArgumentException {
        missingParameters(config, emd);

        if (TextUtil.isEmpty(email.getAddress()))
            emd.addMessage(config.getString("email.address_null"));

        if (!TextUtil.isEmail(email.getAddress())) {
            String msg = String.format(config.getString("email.address_valid"),
                    email.getAddress());
            emd.addMessage(msg);
        }

        EmailDao controller = EntityControllerFactory.getEmailController();
        if (controller.exists(email.getAddress())) {
            String msg = String.format(config.getString("email.a_exists"),
                    email.getAddress());
            emd.addMessage(msg);
        }
    }

    /**
     * Evaluate if the phone object contains errors or missing requeriments to
     * meet.
     * <p>
     * If this the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param phone phone to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluatePhone(PhoneRequest phone, ErrorMessageData emd,
            ConfigLanguage config) throws IllegalArgumentException {
        missingParameters(config, emd);

        if (TextUtil.isEmpty(phone.getNumber()))
            emd.addMessage(config.getString("phone.number_null"));

        if (!TextUtil.isPhone(phone.getNumber())) {
            String msg = String.format(config.getString("phone.is_valid"),
                    phone.getNumber());
            emd.addMessage(msg);
        }

        PhoneDao controller = EntityControllerFactory.getPhoneController();
        if (controller.exists(phone.getNumber())) {
            String msg = String.format(config.getString("phone.n_exists"),
                    phone.getNumber());
            emd.addMessage(msg);
        }
    }

}
