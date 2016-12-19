package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.dao.MobileNumberDao;
import com.nativapps.arpia.database.dao.OperationDao;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.MobileNumber;
import com.nativapps.arpia.database.entity.Operation;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.MobileNumberRequest;
import com.nativapps.arpia.model.dto.MobileNumberResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.MobileNumberValidator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MobileNumberServiceImpl extends GenericService
        implements MobileNumberService,
        DtoConverter<MobileNumber, MobileNumberRequest, MobileNumberResponse> {

    private final MobileNumberDao mobileNController
            = EntityControllerFactory.getNumberMobileDao();

    private final MessengerDao messengerController
            = EntityControllerFactory.getMessengerController();

    private final OperationDao operationController
            = EntityControllerFactory.getOperationController();

    @Override
    public List<MobileNumberResponse> add(Integer quantity, Long operationId) {
        if (quantity == null || quantity <= 0)
            throw new BadRequestException(
                    config.getString("mobile_number.quantity_is_required"));

        Operation operation = getOperationEntity(operationId);
        List<MobileNumberResponse> responses = new ArrayList<>();
        int maxNumber = mobileNController.findMaxNumber(operationId).getNumber();
        int lastNumber = maxNumber + quantity;
        for (int i = (maxNumber + 1); i <= lastNumber; i++) {
            MobileNumber newMobileNumber = new MobileNumber(i);
            newMobileNumber.setOperation(operation);
            MobileNumber mobileNumber = mobileNController.save(newMobileNumber);

            responses.add(convertToDto(mobileNumber));
        }
        return responses;
    }

    @Override
    public MobileNumberResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    @Override
    public List<MobileNumberResponse> getAll(int start, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MobileNumberResponse update(Long id, MobileNumberRequest data) {
        validateMObileNumber(data);
        MobileNumber current = getEntity(id);
        Messenger messenger = getMessengerEntity(data.getMessengerId());

        current.setNumber(data.getNumber());
        current.setMessenger(messenger);
        current = mobileNController.save(current);

        return convertToDto(current);
    }

    @Override
    public MobileNumberResponse delete(Long id) {
        MobileNumber mobileNumber = getEntity(id);
        mobileNController.delete(id);

        return convertToDto(mobileNumber);
    }

    @Override
    public MobileNumber convertToEntity(MobileNumberRequest data) {
        return new MobileNumber(data.getNumber());
    }

    @Override
    public MobileNumberResponse convertToDto(MobileNumber entity) {
        return new MobileNumberResponse(entity.getId(), entity.getNumber(),
                entity.getMessenger().getId(), entity.getOperation().getId());
    }

    /**
     * Validate if mobile number have all attributes to save in the database.
     *
     * @param data
     *
     * @throws BadRequestException
     * @throws ServiceException
     */
    private void validateMObileNumber(MobileNumberRequest data)
            throws BadRequestException, ServiceException {
        ErrorMessageData emd = new ErrorMessageData();
        MobileNumberValidator.evaluateMobileNumber(data, emd, config);

        //verify if exist error messages
        if (!emd.getMessages().isEmpty()) {
            emd.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(emd);
        }
    }

    /**
     * Get mobile number entity from the given ID.
     *
     * @param id mobile number ID
     * @return mobile number entity
     *
     * @throws BadRequestException if mobile number ID is null or less than or
     * equal to 0
     * @throws NotFoundException if the mobile number obtained is null
     */
    private MobileNumber getEntity(Long id) throws BadRequestException,
            NotFoundException {
        if (id == null || id <= 0)
            throw new BadRequestException(
                    config.getString("mobile_number.id_is_required"));

        MobileNumber mobileNumber = mobileNController.find(id);
        if (mobileNumber == null) {
            String msg = String.format(
                    config.getString("mobile_number.not_found"), id);
            throw new NotFoundException(msg);
        }

        return mobileNumber;
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
     * Get operation entity by id provided
     *
     * @param operationId
     * @return
     */
    private Operation getOperationEntity(Long operationId) {
        if (operationId == null || operationId <= 0)
            throw new BadRequestException(
                    config.getString("operation.id_required"));

        Operation currentOperation = operationController.find(operationId);

        if (currentOperation == null) {
            String msg = String.format(
                    config.getString("operation.not_found"), operationId);
            throw new NotFoundException(msg);
        }
        return currentOperation;
    }
}
