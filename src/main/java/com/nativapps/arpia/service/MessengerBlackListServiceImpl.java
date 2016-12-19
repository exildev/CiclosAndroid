package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.entity.CustomerParameter;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.SimpleMessengerRequest;
import com.nativapps.arpia.model.dto.SimpleMessengerResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import com.nativapps.arpia.database.dao.CustomerParamDao;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class MessengerBlackListServiceImpl extends GenericService implements MessengerBlackListService,
        DtoConverter<Messenger, SimpleMessengerRequest, SimpleMessengerResponse> {

    private final MessengerDao messengerDao = EntityControllerFactory
            .getMessengerController();

    private final CustomerParamDao parameterDao = EntityControllerFactory
            .getCustomerParamController();

    @Override
    public List<SimpleMessengerResponse> getAll(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("customer.id_required"));

        CustomerParameter parameter = parameterDao.find(id);

        if (parameter == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), id));

        List<SimpleMessengerResponse> data = new ArrayList<>();

        for (Messenger messenger : parameter.getMessengerBlackList()) {
            data.add(convertToDto(messenger));
        }

        return data;
    }

    @Override
    public SimpleMessengerResponse get(Long id, Integer index) {
        if (index <= 0)
            throw new BadRequestException(config
                    .getString("customer.index_required"));
        List<SimpleMessengerResponse> list = getAll(id);
        if (index < list.size())
            return getAll(id).get(index - 1);
        throw new NotFoundException(String.format(config
                .getString("messenger.index_not_found"), index));
    }

    @Override
    public SimpleMessengerResponse add(Long id, Long messengerId) {
        ErrorMessageData errors = new ErrorMessageData(Response.Status.BAD_REQUEST.getStatusCode());
        if (id == null || id <= 0)
            errors.addMessage(config.getString("customer.id_required"));
        if (messengerId == null || messengerId <= 0)
            errors.addMessage(config.getString("meesenger.id_required"));
        if (!errors.getMessages().isEmpty())
            throw new ServiceException(errors);

        CustomerParameter parameter = parameterDao.find(id);

        if (parameter == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), id));

        Messenger messenger = messengerDao.find(messengerId);

        if (messenger == null)
            throw new NotFoundException(String.format(config
                    .getString("messenger.not_found"), messengerId));

        parameter.addMessenger(messenger);
        parameterDao.save(parameter);

        return convertToDto(messenger);
    }

    @Override
    public SimpleMessengerResponse delete(Long id, Integer index) {
        if (index <= 0)
            throw new BadRequestException(config
                    .getString("customer.index_required"));
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("customer.id_required"));

        CustomerParameter parameter = parameterDao.find(id);

        if (parameter == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), id));

        if (parameter.getMessengerBlackList() != null
                || index < parameter.getMessengerBlackList().size()) {
            Messenger messenger = parameter.getMessengerBlackList()
                    .get(index - 1);
            parameter.getMessengerBlackList().remove(index - 1);
            parameterDao.save(parameter);
            return convertToDto(messenger);
        }

        throw new NotFoundException(String.format(config
                .getString("messenger.index_not_found"), index));
    }

    @Override
    public Messenger convertToEntity(SimpleMessengerRequest data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SimpleMessengerResponse convertToDto(Messenger entity) {
        return new SimpleMessengerResponse(entity.getIdentification(),
                entity.getName(), entity.getLastName(),
                entity.getGender(), entity.getBirthday());
    }
}
