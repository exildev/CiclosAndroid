package com.nativapps.arpia.service.validator;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.InventoryDao;
import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.InventoryRequest;
import com.nativapps.arpia.util.TextUtil;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class InventoryValidator extends Validator {

    /**
     * Don't let anyone instantiate this class.
     */
    private InventoryValidator() { }

    /**
     * Evaluate if the inventory object contains errors or missing requeriments
     * to meet.
     * <p>
     * If this is the case, then the method add messages to
     * {@link ErrorMessageData} object using the language configuration.
     *
     * @param inventory inventory to evaluate
     * @param emd error message data
     * @param config language configuration
     *
     * @throws IllegalArgumentException if any of the parameters is missing
     */
    public static void evaluateInventory(InventoryRequest inventory,
            ErrorMessageData emd, ConfigLanguage config) {
        missingParameters(config, emd);

        InventoryDao controller = EntityControllerFactory.getInventoryController();
        if (TextUtil.isEmpty(inventory.getName())) {
            emd.addMessage(config.getString("inventory.name"));
        } else if (controller.findByName(inventory.getName()) != null) {
            String msg = String.format(config.getString("inventory.name_unique"),
                    inventory.getName());
            emd.addMessage(msg);
        }

        if (inventory.getCount() == null || inventory.getCount() < 0)
            emd.addMessage(config.getString("inventory.count"));
    }

}
