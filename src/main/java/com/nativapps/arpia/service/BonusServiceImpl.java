package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.BonusDao;
import com.nativapps.arpia.database.dao.CustomerDataDao;
import com.nativapps.arpia.database.dao.UserDao;
import com.nativapps.arpia.database.entity.Bonus;
import com.nativapps.arpia.database.entity.CustomerData;
import com.nativapps.arpia.model.dto.BonusRequest;
import com.nativapps.arpia.model.dto.BonusResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.rest.TokenUtil;
import com.nativapps.arpia.rest.exception.TokenException;
import com.nativapps.arpia.rest.exception.UnauthorizedException;
import com.nativapps.arpia.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BonusServiceImpl extends GenericService implements BonusService,
        DtoConverter<Bonus, BonusRequest, BonusResponse> {

    private final BonusDao bonusDao = EntityControllerFactory
            .getBonusDaoController();

    private final UserDao userDao = EntityControllerFactory.getUserController();

    private final CustomerDataDao customerDataDao = EntityControllerFactory
            .getCustomerDataController();

    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    @Override
    public List<BonusResponse> getAll(int start, int size, Long customerId) {
        ErrorMessageData errors = new ErrorMessageData(
                Response.Status.BAD_REQUEST.getStatusCode());

        if (customerId == null)
            errors.addMessage(config.getString("customer.id_required"));
        if (start < 0)
            errors.addMessage(config.getString("pagination.start"));
        if (size <= 0)
            errors.addMessage(config.getString("pagination.size"));

        if (!errors.getMessages().isEmpty())
            throw new ServiceException(errors);

        CustomerData customerData = customerDataDao.find(customerId);

        if (customerData == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), customerId));

        List<BonusResponse> response = new ArrayList<>();
        List<Bonus> result = bonusDao.findAllByCustomerIdPag(start, size,
                customerId);
        for (Bonus bonus : result) {
            response.add(convertToDto(bonus));
        }

        return response;
    }

    public Bonus getEntity(Long customerId, Long bonusId) {
        if (customerId == null)
            throw new BadRequestException(config
                    .getString("customer.id_required"));
        if (bonusId == null)
            throw new BadRequestException(config
                    .getString("bonus.id_required"));

        CustomerData customerData = customerDataDao.find(customerId);

        if (customerData == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), customerId));

        Bonus bonus = bonusDao.findByCustomerId(customerId, bonusId);

        if (bonus == null)
            throw new NotFoundException(String.format(config
                    .getString("bonus.not_found"), customerId, bonusId));

        return bonus;
    }

    @Override
    public BonusResponse get(Long customerId, Long bonusId) {
        return convertToDto(getEntity(customerId, bonusId));
    }

    @Override
    public BonusResponse add(Long customerId, String authToken,
            BonusRequest bonus) {
        ErrorMessageData errors = new ErrorMessageData(
                Response.Status.BAD_REQUEST.getStatusCode());

        if (customerId == null)
            throw new BadRequestException(config
                    .getString("customer.id_required"));
        if (bonus == null)
            throw new BadRequestException(config
                    .getString("bonus.is_null"));
        else {
            if (bonus.getCountFreeService() == null)
                errors.addMessage(config
                        .getString("bonus.count_free_service_null"));
            if (bonus.getCountFreeService() == null)
                errors.addMessage(config
                        .getString("bonus.count_free_service_used_null"));
            if (bonus.getReason() == null)
                errors.addMessage(config.getString("bonus.reason_required"));
            if (bonus.getExpiryDate() == null)
                errors.addMessage(config.getString("bonus.expiry_date_required"));
        }

        if (!errors.getMessages().isEmpty())
            throw new ServiceException(errors);

        CustomerData customerData = customerDataDao.find(customerId);

        if (customerData == null)
            throw new NotFoundException(String.format(config
                    .getString("customer.not_found"), customerId));

        Bonus entity = convertToEntity(bonus);
        entity.setRegisterDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
        entity.setAvailable(true);
        try {
            authToken = authToken.replace(AUTHORIZATION_PREFIX, "");
            entity.setAuthorizedBy(userDao.find(TokenUtil.validateToken(authToken)
                    .getId()));
        } catch (UnauthorizedException ex) {
            throw new WebApplicationException(ex.getMessage(),
                    Response.Status.UNAUTHORIZED);
        } catch (TokenException ex) {
            throw new WebApplicationException(ex.getMessage(), ex);
        }

        List<Bonus> bonusList = bonusDao.findAllByCustomerId(customerId);
        if (bonusList.size() > 0) {
            Bonus lastBonus = bonusList.get(bonusList.size() - 1);
            if (lastBonus != null) {
                lastBonus.setAvailable(false);
                bonusDao.save(lastBonus);
            }
        }

        entity.setCustomerData(customerData);

        return convertToDto(bonusDao.save(entity));
    }

    @Override
    public BonusResponse update(Long customerId, Long bonusId, String authToken,
            BonusRequest bonus) {
        Bonus entity = getEntity(customerId, bonusId);

        if (bonus.getCountFreeService() != null
                && entity.getCountFreeService() != bonus.getCountFreeService())
            entity.setCountFreeService(bonus.getCountFreeService());
        if (bonus.getCountFreeServiceUsed() != null
                && entity.getCountFreeServiceUsed() != bonus.getCountFreeServiceUsed())
            entity.setCountFreeServiceUsed(bonus.getCountFreeServiceUsed());
        if (bonus.getExpiryDate() != null
                && entity.getExpiryDate().equals(bonus.getExpiryDate()))
            entity.setExpiryDate(bonus.getExpiryDate());
        if (bonus.getReason() != null && entity.getReason()
                .equalsIgnoreCase(bonus.getReason()))
            entity.setReason(bonus.getReason());

        try {
            authToken = authToken.replace(AUTHORIZATION_PREFIX, "");
            entity.setAuthorizedBy(userDao.find(TokenUtil.validateToken(authToken)
                    .getId()));
        } catch (UnauthorizedException ex) {
            throw new WebApplicationException(ex.getMessage(),
                    Response.Status.UNAUTHORIZED);
        } catch (TokenException ex) {
            throw new WebApplicationException(ex.getMessage(), ex);
        }

        return convertToDto(bonusDao.save(entity));
    }

    @Override
    public BonusResponse delete(Long customerId, Long bonusId) {
        BonusResponse bonus = get(customerId, bonusId);
        bonusDao.delete(bonusId);
        return bonus;
    }

    @Override
    public Bonus convertToEntity(BonusRequest data) {
        Bonus bonus = new Bonus();
        bonus.setReason(data.getReason());
        bonus.setCountFreeService(data.getCountFreeService());
        bonus.setCountFreeServiceUsed(data.getCountFreeServiceUsed());
        bonus.setExpiryDate(data.getExpiryDate());

        return bonus;
    }

    @Override
    public BonusResponse convertToDto(Bonus entity) {
        BonusResponse response = new BonusResponse();

        response.setId(entity.getId());
        response.setAuthorizedBy(entity.getAuthorizedBy().getId());
        response.setAvailable(entity.isAvailable());
        response.setCountFreeServiceAvailable(entity.getCountFreeService()
                - entity.getCountFreeServiceUsed());
        response.setCountFreeService(entity.getCountFreeService());
        response.setCountFreeServiceUsed(entity.getCountFreeServiceUsed());
        response.setExpiryDate(entity.getExpiryDate());
        response.setReason(entity.getReason());
        response.setRegisterDate(entity.getRegisterDate());

        return response;
    }
}
