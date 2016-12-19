package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.UserDao;
import com.nativapps.arpia.database.entity.Operation;
import com.nativapps.arpia.database.entity.User;
import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.OperationRequest;
import com.nativapps.arpia.model.dto.OperationResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import com.nativapps.arpia.database.dao.OperationDao;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.1.2
 */
public class OperationServiceImpl extends GenericService
        implements OperationService,
        DtoConverter<Operation, OperationRequest, OperationResponse> {

    private final OperationDao operationController = EntityControllerFactory
            .getOperationController();

    private final UserDao userController = EntityControllerFactory
            .getUserController();

    /**
     * Get information of operation entity from the given operation ID.
     *
     * @param id operation ID.
     * @return operation entity information.
     */
    private Operation getEntityOperation(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config.getString("operation.id_required"));
        Operation operation = operationController.find(id);
        if (operation == null) {
            String m = String.format(config.getString("operation.not_found"), id);
            throw new NotFoundException(m);
        }
        return operation;
    }

    @Override
    public List<OperationResponse> getAll() {
        List<OperationResponse> companies = new ArrayList<>();
        for (Operation operation : operationController.findAll())
            companies.add(convertToDto(operation));
        return companies;
    }

    @Override
    public OperationResponse get(Long id) {
        return convertToDto(getEntityOperation(id));
    }

    @Override
    public OperationResponse getOperationByName(String name) {
        if (TextUtil.isEmpty(name))
            throw new BadRequestException(config.getString("operation.name"));
        Operation operation = operationController.findByName(name);
        if (operation == null) {
            String message = String.format(config.getString("operation.nnf"), name);
            throw new NotFoundException(message);
        }
        return convertToDto(operation);
    }

    @Override
    public OperationResponse add(OperationRequest operation) {
        if (operation == null)
            throw new BadRequestException(config.getString("operation.is_null"));

        int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
        ErrorMessageData emd = new ErrorMessageData(statusCode);

        if (TextUtil.isEmpty(operation.getName()))
            emd.addMessage(config.getString("operation.name"));
        else if (operationController.findByName(operation.getName()) != null)
            emd.addMessage(String.format(config.getString("operation.ne"),
                    operation.getName()));

        if (TextUtil.isEmpty(operation.getAlias()))
            emd.addMessage(config.getString("operation.alias"));
        else if (operationController.findByAlias(operation.getAlias()) != null)
            emd.addMessage(String.format(config.getString("operation.ae"),
                    operation.getAlias()));

        if (TextUtil.isEmpty(operation.getAdministrator()))
            emd.addMessage(config.getString("operation.admin"));

        if (TextUtil.isEmpty(operation.getEmail()))
            emd.addMessage(config.getString("operation.email"));
        else if (!TextUtil.isEmail(operation.getEmail()))
            emd.addMessage(config.getString("operation.e_format"));
        else if (userController.findByEmail(operation.getEmail()) != null)
            emd.addMessage(String.format(config.getString("operation.e_exists"),
                    operation.getEmail()));

        if (TextUtil.isEmpty(operation.getPassword()))
            emd.addMessage("operation.pass");

        if (!emd.getMessages().isEmpty())
            throw new ServiceException(emd);

        User user = userController.save(new User(operation.getEmail(),
                operation.getPassword(), operation.getAdministrator(),
                UserType.ADMINISTRATOR));

        Operation entity = convertToEntity(operation);
        entity.setAdminUser(user);

        Operation operationSaved = null;
        try {
            operationSaved = operationController.save(entity);
            user.setOperations(Arrays.asList(operationSaved));
            userController.save(user);
        } catch (Exception e) {
            userController.delete(user.getId());
            throw new InternalServerErrorException(config
                    .getString("operation.error_creating"), e);
        }

        return convertToDto(operationSaved);
    }

    @Override
    public OperationResponse update(Long id, OperationRequest operation) {
        if (operation == null)
            throw new BadRequestException(config.getString("operation.is_null"));

        Operation currentOperation = getEntityOperation(id);

        if (!TextUtil.isEmpty(operation.getName())
                && !currentOperation.getName().equals(operation.getName())) {
            if (operationController.findByName(operation.getName()) != null) {
                String message = String.format(config.getString("operation.ne"),
                        operation.getName());
                throw new BadRequestException(message);
            }
            currentOperation.setName(operation.getName());
        }

        if (!TextUtil.isEmpty(operation.getAlias())
                && operation.getAlias().equals(currentOperation.getAlias())) {
            if (operationController.findByAlias(operation.getAlias()) != null) {
                String msg = String.format(config.getString("operation.ae"),
                        operation.getAlias());
                throw new BadRequestException(msg);
            }
        }

        if (!TextUtil.isEmpty(operation.getDescription()))
            currentOperation.setDescription(operation.getDescription());
        if (!TextUtil.isEmpty(operation.getImageUrl()))
            currentOperation.setPicUrl(operation.getImageUrl());

        return convertToDto(operationController.save(currentOperation));
    }

    @Override
    public OperationResponse delete(Long id) {
        OperationResponse operation = get(id);
        operationController.delete(id);
        return operation;
    }

    @Override
    public Operation convertToEntity(OperationRequest d) {
        return new Operation(d.getName(), d.getAlias(), d.getDescription(),
                d.getImageUrl());
    }

    @Override
    public OperationResponse convertToDto(Operation e) {
        return new OperationResponse(e.getId(), e.getAdminUser().getId(),
                e.getName(), e.getAlias(), e.getDescription(), e.getPicUrl());
    }

}
