package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.EmailDao;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.dao.PhoneDao;
import com.nativapps.arpia.database.dao.ReferenceDao;
import com.nativapps.arpia.database.entity.Address;
import com.nativapps.arpia.database.entity.Email;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.database.entity.Phone;
import com.nativapps.arpia.database.entity.Reference;
import com.nativapps.arpia.database.entity.TypeReference;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.EmailResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.model.dto.PhoneResponse;
import com.nativapps.arpia.model.dto.ReferenceRequest;
import com.nativapps.arpia.model.dto.ReferenceResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class ReferenceServiceImpl extends GenericService implements ReferenceService,
        DtoConverter<Reference, ReferenceRequest, ReferenceResponse> {

    private final ReferenceDao referenceController = EntityControllerFactory
            .getReferenceController();

    private final MessengerDao messengerController = EntityControllerFactory
            .getMessengerController();

    private final EmailDao emailDao = EntityControllerFactory
            .getEmailController();

    private final PhoneDao phoneDao = EntityControllerFactory
            .getPhoneController();

    private final NeighborhoodDao neighborhoodDao = EntityControllerFactory
            .getNeighborhoodController();

    @Override
    public ReferenceResponse add(Long messengerId, ReferenceRequest data) {
        ErrorMessageData errors = new ErrorMessageData();

        if (data == null)
            throw new BadRequestException(
                    config.getString("reference.required"));

        Messenger messenger = getMessengerEntity(messengerId);

        if (TextUtil.isEmpty(data.getName()))
            errors.addMessage(
                    config.getString("person.name_required"));
        if (TextUtil.isEmpty(data.getLastName()))
            errors.addMessage(
                    config.getString("person.last_name_required"));
        if (data.getTypeReference() == null)
            errors.addMessage(
                    config.getString("reference.type_reference"));
        if (data.getTypeReference() == TypeReference.FAMILY
                && data.getRelationship() == null)
            errors.addMessage(
                    config.getString("reference.relationship"));

        if (data.getAddresses() != null
                && !data.getAddresses().isEmpty()) {
            for (AddressRequest address : data.getAddresses()) {
                if (address.getNeighborhood() == null)
                    errors.addMessage(config
                            .getString("address.neighborhood_null"));
                if (TextUtil.isEmpty(address.getResidentialAddress()))
                    errors.addMessage(config
                            .getString("address.resid_address_null"));
                if (TextUtil.isEmpty(address.getTag()))
                    errors.addMessage(config.getString("address.tag_null"));
            }
        } else
            errors.addMessage(config.getString("addres.at_least"));

        if (data.getEmails() != null
                && !data.getEmails().isEmpty()) {
            for (EmailRequest email : data.getEmails()) {
                if (TextUtil.isEmpty(email.getAddress()))
                    errors.addMessage(config
                            .getString("email.address_null"));
                if (!TextUtil.isEmail(email.getAddress()))
                    errors.addMessage(String.format(config
                            .getString("email.address_valid"),
                            email.getAddress()));
                if (emailDao.exists(email.getAddress()))
                    errors.addMessage(String.format(config
                            .getString("email.a_exists"), email
                            .getAddress()));
            }
        } else
            errors.addMessage(config.getString("email.address_required"));

        if (data.getPhones() != null) {
            for (PhoneRequest pdata : data.getPhones()) {
                if (TextUtil.isEmpty(pdata.getNumber()))
                    errors.addMessage(config
                            .getString("phone.number_null"));
                if (!TextUtil.isPhone(pdata.getNumber()))
                    errors.addMessage(String.format(config
                            .getString("phone.is_valid"),
                            pdata.getNumber()));
                if (phoneDao.exists(pdata.getNumber()))
                    errors.addMessage(String.format(config
                            .getString("phone.n_exists"),
                            pdata.getNumber()));
            }
        } else
            errors.addMessage(config.getString("phone.at_least"));

        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }
        Reference reference = convertToEntity(data);
        reference.setCurriculumVitae(messenger.getCurriculumVitae());
        return convertToDto(referenceController.save(reference));
    }

    @Override
    public ReferenceResponse get(Integer index, Long messengerId) {
        return convertToDto(getEntity(index, messengerId));
    }

    @Override
    public List<ReferenceResponse> getAll(Long messengerId) {
        getMessengerEntity(messengerId);
        List<ReferenceResponse> referenceResponses = new ArrayList<>();

        for (Reference reference
                : referenceController.getAllBycurriculumVitaeId(messengerId)) {
            referenceResponses.add(convertToDto(reference));
        }
        return referenceResponses;
    }

    @Override
    public ReferenceResponse update(Integer index, Long messengerId,
            ReferenceRequest data) {
        ErrorMessageData errors = new ErrorMessageData();
        Reference currentReference = getEntity(index, messengerId);

        if (data == null)
            throw new BadRequestException(
                    config.getString("reference.required"));

        if (TextUtil.isEmpty(data.getName()))
            errors.addMessage(
                    config.getString("person.name_required"));
        if (TextUtil.isEmpty(data.getLastName()))
            errors.addMessage(
                    config.getString("person.last_name_required"));
        if (data.getTypeReference() == null)
            errors.addMessage(
                    config.getString("reference.type_reference"));
        if (data.getTypeReference() == TypeReference.FAMILY
                && data.getRelationship() == null)
            errors.addMessage(
                    config.getString("reference.relationship"));

        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }
        currentReference.setLastName(data.getLastName());
        currentReference.setName(data.getName());
        currentReference.setTypeReference(data.getTypeReference());
        currentReference.setRelationship(data.getRelationship());

        return convertToDto(referenceController.save(currentReference));
    }

    @Override
    public ReferenceResponse delete(Integer index, Long messengerId) {
        Reference reference = getEntity(index, messengerId);
        referenceController.delete(reference.getId());
        return convertToDto(reference);
    }

    @Override
    public Reference convertToEntity(ReferenceRequest data) {
        Reference reference = new Reference();
        reference.setName(data.getName());
        reference.setLastName(data.getLastName());
        reference.setRelationship(data.getRelationship());
        reference.setTypeReference(data.getTypeReference());

        for (AddressRequest adata : data.getAddresses()) {
            Neighborhood neighborhood = neighborhoodDao.find(adata.getNeighborhood());
            if (neighborhood == null)
                throw new NotFoundException(String.format(config
                        .getString("address.neighborhood_not_found"), adata
                        .getNeighborhood()));
            reference.addAddress(new Address(adata.getTag(),
                    adata.getResidentialAddress(), neighborhood));
        }
        for (EmailRequest edata : data.getEmails()) {
            reference.addEmail(new Email(edata.getAddress()));
        }
        for (PhoneRequest pdata : data.getPhones()) {
            reference.addPhone(new Phone(pdata.getTag(), pdata.getNumber(),
                    pdata.getPhoneType()));
        }

        return reference;
    }

    @Override
    public ReferenceResponse convertToDto(Reference entity) {
        List<AddressResponse> addressDatas = new ArrayList<>();
        for (Address address : entity.getAddresses())
            addressDatas.add(new AddressResponse(address.getTag(),
                    address.getResidentialAddress(),
                    new NeighborhoodResponse(address.getNeighborhood().getId(),
                            address.getNeighborhood().getName())));

        List<PhoneResponse> phoneDatas = new ArrayList<>();
        for (Phone phone : entity.getPhones())
            phoneDatas.add(new PhoneResponse(phone.getTag(),
                    phone.getNumber(), phone.getType(), phone.isConfirmed()));

        List<EmailResponse> emailDatas = new ArrayList<>();
        for (Email email : entity.getEmails())
            emailDatas.add(new EmailResponse(email.getAddress(),
                    email.isConfirmed()));

        return new ReferenceResponse(entity.getId(), addressDatas,
                emailDatas, phoneDatas, entity.getName(),
                entity.getLastName(),
                entity.getTypeReference(), entity.getRelationship());
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
     * Gets messenger by id provided
     *
     * @param index
     * @param messengerId
     * @return current messenger
     */
    public Reference getEntity(Integer index, Long messengerId) {
        if (index == null || index <= 0)
            throw new BadRequestException(
                    config.getString("reference.index_required"));

        getMessengerEntity(messengerId);

        List<Reference> references = (ArrayList<Reference>) referenceController.
                getAllBycurriculumVitaeId(messengerId);

        if (references == null) {
            String msg = String.format(
                    config.getString("reference.index_not_found"), index);
            throw new NotFoundException(msg);
        }

        if (references.isEmpty())
            throw new NotFoundException(
                    config.getString("reference.is_empty"));

        return references.get(index - 1);
    }

}
