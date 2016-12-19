package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.entity.CustomerParameter;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.ParticularParam;
import com.nativapps.arpia.model.dto.ParticularParamRequest;
import com.nativapps.arpia.model.dto.ParticularParamResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import com.nativapps.arpia.database.dao.CustomerParamDao;
import com.nativapps.arpia.database.dao.ParticularDao;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class ParticularParamServiceImpl extends GenericService implements ParticularParamService,
        DtoConverter<CustomerParameter, ParticularParamRequest, ParticularParamResponse> {

    private final CustomerParamDao parameterDao
            = EntityControllerFactory.getCustomerParamController();

    private final MessengerDao messengerDao = EntityControllerFactory
            .getMessengerController();

    private final ParticularDao particularDao = EntityControllerFactory
            .getParticularController();

    @Override
    public ParticularParamResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    /**
     * Returns a particular parameter entity
     *
     * @param id Particular ID
     * @return Searched particular parameter entity
     */
    public CustomerParameter getEntity(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("particular.id_required"));
        if (particularDao.findByCustomerId(id) == null)
            throw new NotFoundException(String.format(config
                    .getString("particular.not_found"), id));

        CustomerParameter parameters = parameterDao.find(id);
        if (parameters == null)
            throw new NotFoundException(String.format(config
                    .getString("particular.not_found"), id));
        return parameters;
    }

    @Override
    public ParticularParamResponse update(Long id,
            ParticularParamRequest data) {
        if (data == null)
            throw new BadRequestException(config.getString("particular_param.is_null"));

        CustomerParameter parameters = getEntity(id);
        if (data.getUrgentOrders() != null && data.getUrgentOrders()
                != parameters.isUrgentOrders())
            parameters.setUrgentOrders(data.getUrgentOrders());
        if (data.getAlwaysMoneyReturn() != null && data.getAlwaysMoneyReturn()
                != parameters.isAlwaysMoneyReturn())
            parameters.setAlwaysMoneyReturn(data.getAlwaysMoneyReturn());
        if (data.getSpecialRateInfo() != null && !data.getSpecialRateInfo()
                .equalsIgnoreCase(parameters.getSpecialRateInfo()))
            parameters.setSpecialRateInfo(data.getSpecialRateInfo());
        if (data.getDenied() != null && data.getDenied() != parameters.isDenied())
            parameters.setDenied(data.getDenied());
        if (data.getCondition() != null && !data.getCondition()
                .equalsIgnoreCase(parameters.getCondition()))
            parameters.setCondition(data.getCondition());
        if (data.getFavoriteMessengerId() != null) {
            if (parameters.getFavoriteMessenger() == null
                    || data.getFavoriteMessengerId() != parameters
                    .getFavoriteMessenger().getId()) {
                Messenger messenger = messengerDao.find(data
                        .getFavoriteMessengerId());
                if (messenger == null)
                    throw new NotFoundException(String.format(config
                            .getString("messenger.not_found"), data
                            .getFavoriteMessengerId()));
                parameters.setFavoriteMessenger(messenger);
            }
        }
        if (data.getTracing() != null) {
            parameters.getTracing().setReportPeriod(data.getTracing()
                    .getReportPeriod());
            if (data.getTracing().getReports() != null)
                parameters.getTracing().setReports(data.getTracing()
                        .getReports());
        }

        return convertToDto(parameterDao.save(parameters));
    }

    @Override
    public CustomerParameter convertToEntity(ParticularParamRequest data) {
        ParticularParam parameters = new ParticularParam();

        parameters.setUrgentOrders(data.getUrgentOrders());
        parameters.setAlwaysMoneyReturn(data.getAlwaysMoneyReturn());
        parameters.setSpecialRateInfo(data.getSpecialRateInfo());
        parameters.setDenied(data.getDenied());
        parameters.setCondition(data.getCondition());
        if (data.getTracing() != null) {
            parameters.getTracing().setReportPeriod(data.getTracing()
                    .getReportPeriod());
            if (data.getTracing().getReports() != null)
                parameters.getTracing().setReports(data.getTracing()
                        .getReports());
        }

        return parameters;
    }

    @Override
    public ParticularParamResponse convertToDto(CustomerParameter entity) {
        ParticularParamResponse parameters
                = new ParticularParamResponse();

        parameters.setUrgentOrders(entity.isUrgentOrders());
        parameters.setAlwaysMoneyReturn(entity.isAlwaysMoneyReturn());
        parameters.setSpecialRateInfo(entity.getSpecialRateInfo());
        parameters.setDenied(entity.isDenied());
        if (entity.getCondition() != null)
            parameters.setCondition(entity.getCondition());
        if (entity.getFavoriteMessenger() != null)
            parameters.setFavoriteMessengerId(entity.getFavoriteMessenger()
                    .getId());
        if (entity.getTracing() != null) {
            parameters.getTracing().setReportPeriod(entity.getTracing()
                    .getReportPeriod());
            if (entity.getTracing().getReports() != null)
                parameters.getTracing().setReports(entity.getTracing()
                        .getReports());
        }

        return parameters;
    }
}
