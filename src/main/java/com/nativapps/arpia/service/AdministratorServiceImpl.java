package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.AdministratorDao;
import com.nativapps.arpia.database.dao.EmailDao;
import com.nativapps.arpia.database.dao.EstablishmentDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.dao.PhoneDao;
import com.nativapps.arpia.database.entity.Address;
import com.nativapps.arpia.database.entity.Administrator;
import com.nativapps.arpia.database.entity.Email;
import com.nativapps.arpia.database.entity.Establishment;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.database.entity.Phone;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.model.dto.AdministratorRequest;
import com.nativapps.arpia.model.dto.AdministratorResponse;
import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.EmailResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.model.dto.PhoneResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AdministratorServiceImpl extends GenericService implements AdministratorService,
        DtoConverter<Administrator, AdministratorRequest, AdministratorResponse> {

    private final AdministratorDao administratorDao = EntityControllerFactory
            .getAdministratorController();

    private final EstablishmentDao establishmentDao = EntityControllerFactory
            .getEstablishmentController();

    private final EmailDao emailDao = EntityControllerFactory
            .getEmailController();

    private final PhoneDao phoneDao = EntityControllerFactory
            .getPhoneController();

    private final NeighborhoodDao neighborhoodDao = EntityControllerFactory
            .getNeighborhoodController();

    @Override
    public AdministratorResponse add(Long establishmentId,
            AdministratorRequest data) {
        if (establishmentId == null || establishmentId <= 0)
            throw new BadRequestException(config
                    .getString("establishment.id_required"));
        else if (data == null)
            throw new BadRequestException(config
                    .getString("administrator.is_null"));
        else {
            ErrorMessageData errorData = new ErrorMessageData();
            if (data.getIdentification() == null)
                errorData.addMessage(config
                        .getString("person.identification_required"));
            if (data.getName() == null)
                errorData.addMessage(config
                        .getString("person.name_required"));
            if (data.getLastName() == null)
                errorData.addMessage(config
                        .getString("person.last_name_required"));
            if (data.getGender() == null)
                errorData.addMessage(config
                        .getString("person.gender_required"));
            if (data.getLinked() == null)
                errorData.addMessage(config
                        .getString("administrator.state_required"));

            if (data.getAddresses() != null) {
                for (AddressRequest address : data.getAddresses()) {
                    if (address.getNeighborhood() == null)
                        errorData.addMessage(config
                                .getString("address.neighborhood_null"));
                    if (TextUtil.isEmpty(address.getResidentialAddress()))
                        errorData.addMessage(config
                                .getString("address.resid_address_null"));
                    if (TextUtil.isEmpty(address.getTag()))
                        errorData.addMessage(config.getString("address.tag_null"));
                }
            } else
                errorData.addMessage(config.getString("address.at_least"));

            if (data.getEmails() != null) {
                for (EmailRequest edata : data.getEmails()) {
                    if (TextUtil.isEmpty(edata.getAddress()))
                        errorData.addMessage(config
                                .getString("email.address_null"));
                    if (!TextUtil.isEmail(edata.getAddress()))
                        errorData.addMessage(String.format(config
                                .getString("email.address_valid"), edata
                                .getAddress()));
                    if (emailDao.exists(edata.getAddress()))
                        errorData.addMessage(String.format(config
                                .getString("email.a_exists"), edata
                                .getAddress()));
                }
            }

            if (data.getPhones() != null) {
                for (PhoneRequest pdata : data.getPhones()) {
                    if (TextUtil.isEmpty(pdata.getNumber()))
                        errorData.addMessage(config
                                .getString("phone.number_null"));
                    if (!TextUtil.isPhone(pdata.getNumber()))
                        errorData.addMessage(String.format(config
                                .getString("phone.is_valid"), pdata.getNumber()));
                    if (phoneDao.exists(pdata.getNumber()))
                        errorData.addMessage(String.format(config
                                .getString("phone.n_exists"), pdata.getNumber()));
                }
            } else
                errorData.addMessage(config.getString("phone.at_least"));

            if (!errorData.getMessages().isEmpty()) {
                errorData.setStatusCode(Response.Status.BAD_REQUEST
                        .getStatusCode());
                throw new ServiceException(errorData);
            }
        }

        Establishment establishment = establishmentDao.find(establishmentId);

        if (establishment == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), establishmentId));

        Administrator administrator = convertToEntity(data);
        administrator.setEstablishment(establishment);

        return convertToDto(administratorDao.save(administrator));
    }

    @Override
    public AdministratorResponse get(Long establishmentId, Long id) {
        return convertToDto(getEntity(id, establishmentId));
    }

    @Override
    public List<AdministratorResponse> getAll(Long establishmentId) {
        if (establishmentId == null || establishmentId <= 0)
            throw new BadRequestException(config
                    .getString("establishment.id_required"));

        Establishment establishment = establishmentDao.find(establishmentId);
        if (establishment == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), establishmentId));

        List<Administrator> list = administratorDao
                .getAllByEstablishmentId(establishmentId);

        List<AdministratorResponse> result = new ArrayList<>();
        for (Administrator administrator : list)
            result.add(convertToDto(administrator));

        return result;
    }

    /**
     * Returns the administrator entity from id provided
     *
     * @param id Administrator's ID
     * @param establishmentId Establishment's ID
     * @return searched entity
     */
    public Administrator getEntity(Long id, Long establishmentId) {
        ErrorMessageData errorData = new ErrorMessageData();
        if (establishmentId == null || establishmentId <= 0)
            errorData.addMessage(config.getString("establishment.id_required"));
        if (id == null || id <= 0)
            errorData.addMessage(config.getString("administrator.id_required"));

        if (!errorData.getMessages().isEmpty()) {
            errorData.setStatusCode(Response.Status.BAD_REQUEST
                    .getStatusCode());
            throw new ServiceException(errorData);
        }

        Establishment establishment = establishmentDao.find(establishmentId);
        if (establishment == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), establishmentId));

        Administrator administrator = administratorDao.getByEstablishmentId(id,
                establishmentId);

        if (administrator == null)
            throw new NotFoundException(String.format(config
                    .getString("administrator.not_found"), establishmentId, id));

        return administrator;
    }

    @Override
    public AdministratorResponse update(Long establishmentId, Long id,
            AdministratorRequest data) {

        if (data == null)
            throw new BadRequestException(config
                    .getString("administrator.is_null"));

        Administrator entity = getEntity(id, establishmentId);

        if (!TextUtil.isEmpty(data.getIdentification()) && !data
                .getIdentification().equals(entity.getIdentification()))
            entity.setIdentification(data.getIdentification());
        if (!TextUtil.isEmpty(data.getName()) && !data.getName()
                .equalsIgnoreCase(entity.getName()))
            entity.setName(data.getName());
        if (!TextUtil.isEmpty(data.getLastName()) && !data.getLastName()
                .equalsIgnoreCase(entity.getLastName()))
            entity.setLastName(data.getLastName());
        if (!TextUtil.isEmpty(data.getObservations()) && !data.getObservations()
                .equalsIgnoreCase(entity.getObservations()))
            entity.setObservations(data.getObservations());
        if (data.getGender() != null && data.getGender() != entity.getGender())
            entity.setGender(data.getGender());
        if (data.getBirthday() != null && !data.getBirthday().equals(entity
                .getBirthday()))
            entity.setBirthday(data.getBirthday());
        if (data.getnDayReport() != null && !Objects.equals(data
                .getnDayReport(), entity.getnDayReport()))
            entity.setnDayReport(data.getnDayReport());
        if (data.getLinked() != null && data.getLinked() != entity.isLinked())
            entity.setLinked(data.getLinked());

        return convertToDto(administratorDao.save(entity));
    }

    @Override
    public AdministratorResponse delete(Long establishmentId, Long id) {
        AdministratorResponse data = get(id, establishmentId);
        administratorDao.delete(id);
        return data;
    }

    @Override
    public Administrator convertToEntity(AdministratorRequest data) {
        Administrator administrator = new Administrator(data.getObservations(),
                data.getLinked(), data.getnDayReport(),
                data.getIdentification(), data.getName(), data.getLastName(),
                data.getGender(), data.getBirthday());

        for (AddressRequest adata : data.getAddresses()) {
            Neighborhood neighborhood = neighborhoodDao.find(adata.getNeighborhood());
            if (neighborhood == null)
                throw new NotFoundException(String.format(config
                        .getString("address.neighborhood_not_found"), adata
                        .getNeighborhood()));

            administrator.addAddress(new Address(adata.getTag(),
                    adata.getResidentialAddress(), neighborhood));
        }

        for (EmailRequest edata : data.getEmails()) {
            administrator.addEmail(new Email(edata.getAddress()));
        }

        for (PhoneRequest pdata : data.getPhones()) {
            administrator.addPhone(new Phone(pdata.getTag(), pdata.getNumber(),
                    pdata.getPhoneType()));
        }

        return administrator;
    }

    @Override
    public AdministratorResponse convertToDto(Administrator entity) {
        AdministratorResponse data
                = new AdministratorResponse(entity.getId(),
                        entity.getIdentification(), entity.getName(),
                        entity.getLastName(), entity.getGender(),
                        entity.getBirthday(), entity.isLinked(),
                        entity.getnDayReport(), entity.getObservations());

        for (Address address : entity.getAddresses())
            data.getAddresses().add(new AddressResponse(address.getId(),
                    address.getTag(), address.getResidentialAddress(),
                    new NeighborhoodResponse(address.getNeighborhood().getId(),
                            address.getNeighborhood().getName())));
        for (Email email : entity.getEmails())
            data.getEmails().add(new EmailResponse(email.getAddress(),
                    email.isConfirmed()));
        for (Phone phone : entity.getPhones())
            data.getPhones().add(new PhoneResponse(phone.getTag(),
                    phone.getNumber(), phone.getType(), phone.isConfirmed()));

        return data;
    }

}
