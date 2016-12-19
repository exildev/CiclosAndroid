package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.FaultDao;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.entity.Fault;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.FaultRequest;
import com.nativapps.arpia.model.dto.FaultResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class FaultServiceImpl extends GenericService
        implements FaultService,
        DtoConverter<Fault, FaultRequest, FaultResponse> {

    private final FaultDao faultController = EntityControllerFactory
            .getFaultController();

    private final MessengerDao messengerController = EntityControllerFactory
            .getMessengerController();

    @Override
    public FaultResponse add(Long messengerId, FaultRequest data) {
        Messenger messenger = getMessengerEntity(messengerId);
        validateData(data);
        Fault fault = convertToEntity(data);
        fault.setMessenger(messenger);

        return convertToDto(faultController.save(fault));
    }

    @Override
    public FaultResponse get(Integer index, Long messengerId) {
        Fault fault = getEntity(index, messengerId);
        return convertToDto(fault);
    }

    @Override
    public List<FaultResponse> getAll(Long messengerId) {
        getMessengerEntity(messengerId);
        List<FaultResponse> faultDatas = new ArrayList<>();
        for (Fault fault : faultController.getAllByMessengerId(messengerId)) {
            faultDatas.add(convertToDto(fault));
        }
        return faultDatas;
    }

    @Override
    public FaultResponse update(Integer index, Long messengerId,
            FaultRequest data) {
        Fault currentFault = getEntity(index, messengerId);
        validateData(data);

        currentFault.setDescription(data.getDescription());
        return convertToDto(faultController.save(currentFault));
    }

    @Override
    public FaultResponse delete(Integer index, Long messengerId) {
        Fault fault = getEntity(index, messengerId);
        faultController.delete(fault.getId());
        return convertToDto(fault);
    }

    @Override
    public Fault convertToEntity(FaultRequest data) {
        return new Fault(data.getDescription());
    }

    @Override
    public FaultResponse convertToDto(Fault entity) {
        return new FaultResponse(entity.getId(), entity.getDescription());
    }

    /**
     * Get messenger entity by messenger id provided
     *
     * @param messengerId
     * @return
     */
    private Messenger getMessengerEntity(Long messengerId) {
        if (messengerId == null || messengerId <= 0)
            throw new BadRequestException(
                    config.getString("meesenger.id_required"));

        Messenger currentMessenger = messengerController.find(messengerId);

        if (currentMessenger == null) {
            String msg = String.format(
                    config.getString("messenger.not_found"), messengerId);
            throw new NotFoundException(msg);
        }
        return currentMessenger;
    }

    /**
     * Gets messenger fault by index provided
     *
     * @param index
     * @param messengerId
     * @return messenger fault
     */
    public Fault getEntity(Integer index, Long messengerId) {
        getMessengerEntity(messengerId);

        if (index == null || index <= 0)
            throw new BadRequestException(
                    config.getString("fault.index_required"));

        List<Fault> faults = (ArrayList<Fault>) faultController.
                getAllByMessengerId(messengerId);

        if (faults == null) {
            String msg = String.format(
                    config.getString("fault.index_not_found"), index);
            throw new NotFoundException(msg);
        }

        if ((index - 1) > faults.size()) {
            String msg = String.format(
                    config.getString("fault.index_off_bounds"), index);
            throw new NotFoundException(msg);
        }

        if (faults.isEmpty())
            throw new NotFoundException(
                    config.getString("fault.is_empty"));

        return faults.get(index - 1);
    }

    /**
     * Validate if the data has the required structure.
     *
     * @param data
     */
    private void validateData(FaultRequest data) {
        ErrorMessageData errors = new ErrorMessageData();

        if (data == null)
            throw new BadRequestException(
                    config.getString("fault.required"));

        if (TextUtil.isEmpty(data.getDescription()))
            errors.addMessage(
                    config.getString("fault.description"));

        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }
    }
}
