package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.entity.CustomerParameter;
import com.nativapps.arpia.database.entity.EstablishmentParam;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.model.dto.EstablishmentParamRequest;
import com.nativapps.arpia.model.dto.EstablishmentParamResponse;
import java.util.Objects;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import com.nativapps.arpia.database.dao.CustomerParamDao;
import com.nativapps.arpia.database.dao.EstablishmentDao;

public class EstablishmentParamServiceImpl extends GenericService implements EstablishmentParamService,
        DtoConverter<CustomerParameter, EstablishmentParamRequest, EstablishmentParamResponse> {

    private final CustomerParamDao parameterDao
            = EntityControllerFactory.getCustomerParamController();

    private final MessengerDao messengerDao = EntityControllerFactory
            .getMessengerController();

    private final EstablishmentDao establishmentDao = EntityControllerFactory
            .getEstablishmentController();

    @Override
    public EstablishmentParamResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    /**
     * Returns a establishment parameter entity
     *
     * @param id Establishment ID
     * @return Searched establishment parameter entity
     */
    public CustomerParameter getEntity(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("establishment.id_required"));
        if (establishmentDao.findByCustomerId(id) == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), id));
        CustomerParameter parameters = parameterDao.find(id);
        if (parameters == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), id));

        return parameters;
    }

    @Override
    public EstablishmentParamResponse update(Long id,
            EstablishmentParamRequest data) {
        if (data == null)
            throw new BadRequestException(config
                    .getString("establishment_param.is_null"));

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
        if (data.getCondition() != null && data.getCondition()
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
            if (data.getTracing().getReportPeriod() != null)
                parameters.getTracing().setReports(data.getTracing()
                        .getReports());
        }
        if (parameters instanceof EstablishmentParam) {
            if (data.getCommissionPerService() != null && !Objects.equals(data
                    .getCommissionPerService(), ((EstablishmentParam) parameters)
                            .getCommissionPerService()))
                ((EstablishmentParam) parameters)
                        .setCommissionPerService(data.getCommissionPerService());
            if (data.getNumberOfServices() != null && data.getNumberOfServices()
                    != ((EstablishmentParam) parameters).getNumberOfServices())
                ((EstablishmentParam) parameters)
                        .setNumberOfServices(data.getNumberOfServices());
            if (data.getDateInitialReport() != null && data.getDateInitialReport()
                    .equals(((EstablishmentParam) parameters)
                            .getDateInitialReport()))
                ((EstablishmentParam) parameters)
                        .setDateInitialReport(data.getDateInitialReport());
            if (data.getPeriod() != null && data.getPeriod()
                    != ((EstablishmentParam) parameters).getPeriod())
                ((EstablishmentParam) parameters)
                        .setPeriod(data.getPeriod());
        }

        return convertToDto(parameterDao.save(parameters));
    }

    @Override
    public CustomerParameter convertToEntity(EstablishmentParamRequest data) {
        CustomerParameter parameters = new EstablishmentParam();

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
        if (parameters instanceof EstablishmentParam) {
            ((EstablishmentParam) parameters)
                    .setCommissionPerService(data.getCommissionPerService());
            ((EstablishmentParam) parameters)
                    .setNumberOfServices(data.getNumberOfServices());
            ((EstablishmentParam) parameters)
                    .setDateInitialReport(data.getDateInitialReport());
            ((EstablishmentParam) parameters)
                    .setPeriod(data.getPeriod());
        }

        return parameters;
    }

    @Override
    public EstablishmentParamResponse convertToDto(CustomerParameter entity) {
        EstablishmentParamResponse parameters
                = new EstablishmentParamResponse();

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
        if (entity instanceof EstablishmentParam) {
            if (((EstablishmentParam) entity).getCommissionPerService()
                    != null)
                parameters.setCommissionPerService(((EstablishmentParam) entity)
                        .getCommissionPerService());

            parameters.setNumberOfServices(((EstablishmentParam) entity)
                    .getNumberOfServices());

            if (((EstablishmentParam) entity).getDateInitialReport()
                    != null)
                parameters.setDateInitialReport(((EstablishmentParam) entity)
                        .getDateInitialReport());

            if (((EstablishmentParam) entity).getPeriod() != null)
                parameters.setPeriod(((EstablishmentParam) entity)
                        .getPeriod());
        }

        return parameters;
    }
}
