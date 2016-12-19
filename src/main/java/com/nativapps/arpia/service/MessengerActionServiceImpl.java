package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerActionDao;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.MessengerAction;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.MessengerActionRequest;
import com.nativapps.arpia.model.dto.MessengerActionResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.ActionValidator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class MessengerActionServiceImpl extends GenericService implements MessengerActionService,
        DtoConverter<MessengerAction, MessengerActionRequest, MessengerActionResponse> {

    private final MessengerActionDao controller
            = EntityControllerFactory.getMessengerActionController();

    /**
     * Validate if messenger action have all attributes to save in database.
     *
     * @param action the action data.
     */
    private void validateMessengerAction(MessengerActionRequest action) {
        if (action == null)
            throw new BadRequestException(config.getString("m_action.is_null"));

        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);
        ActionValidator.evaluateAction(action, emd, config);

        //verify if exists error messages
        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);
    }

    /**
     * Get messenger action entity from the given ID.
     *
     * @param id the action ID
     * @return the action information
     *
     * @throws NotFoundException if the action data is null
     * @throws BadRequestException if the action ID is null or less than or
     * equal to 0
     */
    private MessengerAction getActionEntity(Long id) throws NotFoundException,
            BadRequestException {
        if (id == null || id <= 0)
            throw new BadRequestException(config.getString("m_action.id_required"));

        MessengerAction action = controller.find(id);
        if (action == null) {
            String m = String.format(config.getString("m_action.not_found"), id);
            throw new NotFoundException(m);
        }
        return action;
    }

    /**
     * Get messenger entity from the given messenger ID.
     *
     * @param messengerId the messenger ID
     * @return the messenger information
     *
     * @throws BadRequestException if the messenger ID is null or less than or
     * equal to 0
     * @throws NotFoundException if the messenger information is null
     */
    private Messenger getMessengerEntity(Long messengerId)
            throws BadRequestException, NotFoundException {
        if (messengerId == null || messengerId <= 0)
            throw new BadRequestException(config.getString("messenger.id_required"));

        MessengerDao messengerController = EntityControllerFactory
                .getMessengerController();
        Messenger messenger = messengerController.find(messengerId);
        if (messenger == null) {
            String msg = String.format(config.getString("messenger.not_found"),
                    messengerId);
            throw new NotFoundException(msg);
        }

        return messenger;
    }

    @Override
    public MessengerActionResponse add(Long messengerId,
            MessengerActionRequest action) {
        validateMessengerAction(action);

        MessengerAction actionEntity = convertToEntity(action);
        actionEntity.setMessenger(getMessengerEntity(messengerId));

        return convertToDto(controller.save(actionEntity));
    }

    @Override
    public MessengerActionResponse get(Long id) {
        return convertToDto(getActionEntity(id));
    }

    @Override
    public List<MessengerActionResponse> getAll(Long messengerId, int start, int size) {
        getMessengerEntity(messengerId);
        List<MessengerActionResponse> actions = new ArrayList<>();
        for (MessengerAction action : controller.findAll(start, size, messengerId))
            actions.add(convertToDto(action));
        return actions;
    }

    @Override
    public List<MessengerActionResponse> getAll() {
        List<MessengerActionResponse> actions = new ArrayList<>();
        for (MessengerAction action : controller.findAll())
            actions.add(convertToDto(action));
        return actions;
    }

    @Override
    public MessengerActionResponse update(Long id, MessengerActionRequest action) {
        validateMessengerAction(action);
        MessengerAction currentAction = getActionEntity(id);
        //change attributes of action
        return convertToDto(controller.save(currentAction));
    }

    @Override
    public MessengerActionResponse delete(Long id) {
        MessengerActionResponse action = get(id);
        controller.delete(id);
        return action;
    }

    @Override
    public MessengerAction convertToEntity(MessengerActionRequest d) {
        return new MessengerAction(d.getTitle(), d.getDescription());
    }

    @Override
    public MessengerActionResponse convertToDto(MessengerAction e) {
        return new MessengerActionResponse(e.getId(), e.getRegisterDate(),
                e.getTitle(), e.getDescription());
    }

}
