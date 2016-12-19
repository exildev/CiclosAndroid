package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.entity.Inventory;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import com.nativapps.arpia.database.dao.InventoryDao;
import com.nativapps.arpia.database.dao.InventoryLogDao;
import com.nativapps.arpia.database.entity.InventoryLog;
import com.nativapps.arpia.database.entity.LogType;
import com.nativapps.arpia.model.dto.InventoryRequest;
import com.nativapps.arpia.model.dto.InventoryResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.InventoryValidator;
import com.nativapps.arpia.util.TextUtil;
import java.util.Calendar;
import javax.ws.rs.core.Response;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class InventoryServiceImpl extends GenericService implements InventoryService,
        DtoConverter<Inventory, InventoryRequest, InventoryResponse> {

    private final InventoryDao controller = EntityControllerFactory
            .getInventoryController();

    /**
     * Validate if inventory have all attributes to save in the database.
     *
     * @param inventory inventory to validate
     *
     * @throws BadRequestException if inventory is null
     * @throws ServiceException if inventory data don't have any attributes
     * required
     */
    private void validateInventory(InventoryRequest inventory)
            throws BadRequestException, ServiceException {
        if (inventory == null)
            throw new BadRequestException(config.getString("inventory.is_null"));

        ErrorMessageData emd = new ErrorMessageData();
        InventoryValidator.evaluateInventory(inventory, emd, config);

        //verify if exist error messages
        if (!emd.getMessages().isEmpty()) {
            emd.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(emd);
        }
    }

    /**
     * It lets get to the corresponding inventory entity id provided.
     *
     * @param id inventory index to search
     * @return search inventory
     */
    private Inventory getEntity(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config.getString("inventory.id"));

        Inventory inventory = controller.find(id);
        if (inventory == null) {
            String msg = String.format(config.getString("inventory.not_found"), id);
            throw new NotFoundException(msg);
        }

        return inventory;
    }

    @Override
    public InventoryResponse add(InventoryRequest inventory) {
        validateInventory(inventory);
        Inventory newInventory = controller.save(convertToEntity(inventory));

        //create a first log to inventory element
        InventoryLogDao logController = EntityControllerFactory
                .getInventoryLogController();
        logController.save(new InventoryLog(newInventory, LogType.INPUT,
                newInventory.getCount(), "Entrada por creación del elemento."));

        return convertToDto(newInventory);
    }

    @Override
    public InventoryResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    @Override
    public List<InventoryResponse> getAll() {
        List<InventoryResponse> inventories = new ArrayList();
        for (Inventory inventory : controller.findAll())
            inventories.add(convertToDto(inventory));
        return inventories;
    }

    @Override
    public InventoryResponse update(Long id, InventoryRequest inventory) {
        if (inventory == null)
            throw new BadRequestException(config.getString("inventory.is_null"));

        Inventory currentInventory = getEntity(id);

        if (!TextUtil.isEmpty(inventory.getName())
                && inventory.getName().equalsIgnoreCase(currentInventory.getName()))
            currentInventory.setName(inventory.getName());

        currentInventory.setUpdateDate(Calendar.getInstance());
        return convertToDto(controller.save(currentInventory));
    }

    @Override
    public InventoryResponse delete(Long id) {
        InventoryResponse inventory = get(id);
        controller.delete(id);
        return inventory;
    }

    @Override
    public InventoryResponse convertToDto(Inventory e) {
        return new InventoryResponse(e.getId(), e.getCountUsed(),
                e.getRegisterDate(), e.getUpdateDate(), e.getName(), e.getCount());
    }

    @Override
    public Inventory convertToEntity(InventoryRequest d) {
        return new Inventory(d.getName(), d.getCount());
    }

}
