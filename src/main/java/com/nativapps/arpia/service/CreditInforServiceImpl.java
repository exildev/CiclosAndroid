package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.CreditInfoDao;
import com.nativapps.arpia.database.dao.PersonDao;
import com.nativapps.arpia.database.entity.CreditInfo;
import com.nativapps.arpia.database.entity.Person;
import com.nativapps.arpia.model.dto.CreditInfoDataRequest;
import com.nativapps.arpia.model.dto.CreditInfoDataResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CreditInforServiceImpl extends GenericService implements CreditInfoService,
        DtoConverter<CreditInfo, CreditInfoDataRequest, CreditInfoDataResponse> {

    private final CreditInfoDao creditInfoDao = EntityControllerFactory
            .getCreditInfoController();

    private final PersonDao personDao = EntityControllerFactory
            .getPersonController();

    @Override
    public CreditInfoDataResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    /**
     * Return a CreditInfo entity
     *
     * @param id Customer ID
     * @return CreditInfo entity
     */
    public CreditInfo getEntity(Long id) {
        if (id == null)
            throw new BadRequestException(config
                    .getString("customer.id_required"));

        CreditInfo creditInfo = creditInfoDao.find(id);

        if (creditInfo == null)
            throw new NotFoundException("customer.not_found");

        return creditInfo;
    }

    @Override
    public CreditInfoDataResponse update(Long customerId, CreditInfoDataRequest data) {
        CreditInfo entity = getEntity(customerId);

        if (data == null)
            throw new BadRequestException(config.getString("creditInfo.is_null"));

        if (data.getBill() != null)
            entity.setBill(data.getBill());
        if (data.getPaymentCheckout() != null)
            entity.setPaymentCheckout(data.getPaymentCheckout());
        if (data.getAuthorizedCredit() != null)
            entity.setAuthorizedCredit(data.getPaymentCheckout());
        if (data.getRetefuente() != null)
            entity.setRetefuente(data.getRetefuente());
        if (data.getReteica() != null) {
            if (data.getReteica() < 0 || data.getReteica() > 1)
                throw new BadRequestException(config
                        .getString("creditInfo.reteica"));
            entity.setReteica(data.getReteica());
        }
        if (data.getPersonInCharge() != null) {
            Person person = personDao.find(data.getPersonInCharge());
            if (person == null)
                throw new NotFoundException(String.format(config
                        .getString("person.not_found"), data
                        .getPersonInCharge()));
            entity.setPersonInCharge(person);
        }

        return convertToDto(creditInfoDao.save(entity));
    }

    @Override
    public CreditInfo convertToEntity(CreditInfoDataRequest data) {
        CreditInfo creditInfo = new CreditInfo();

        creditInfo.setBill(data.getBill());
        creditInfo.setAuthorizedCredit(data.getAuthorizedCredit());
        creditInfo.setPaymentCheckout(data.getRetefuente());
        creditInfo.setRetefuente(data.getRetefuente());
        creditInfo.setReteica(data.getReteica());

        return creditInfo;
    }

    @Override
    public CreditInfoDataResponse convertToDto(CreditInfo entity) {
        CreditInfoDataResponse response = new CreditInfoDataResponse();

        response.setId(entity.getId());
        response.setBill(entity.isBill());
        response.setAuthorizedCredit(entity.isAuthorizedCredit());
        response.setPaymentCheckout(entity.isPaymentCheckout());
        response.setRetefuente(entity.isRetefuente());
        response.setReteica(entity.getReteica());
        if (entity.getPersonInCharge() != null)
            response.setPersonInCharge(entity.getPersonInCharge().getId());

        return response;
    }

}
