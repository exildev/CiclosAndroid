package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.entity.Address;
import com.nativapps.arpia.database.entity.CurriculumVitae;
import com.nativapps.arpia.database.entity.Email;
import com.nativapps.arpia.database.entity.Home;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.database.entity.Phone;
import com.nativapps.arpia.database.entity.SocialSecurity;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.model.dto.CurriculumVitaeData;
import com.nativapps.arpia.model.dto.CurriculumVitaeRequest;
import com.nativapps.arpia.model.dto.CurriculumVitaeResponse;
import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.EmailResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.HomeData;
import com.nativapps.arpia.model.dto.MessengerRequest;
import com.nativapps.arpia.model.dto.MessengerResponse;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.model.dto.PhoneResponse;
import com.nativapps.arpia.model.dto.SocialSecurityData;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.MessengerValidator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.1.2
 */
public class MessengerServiceImpl extends GenericService implements MessengerService,
        DtoConverter<Messenger, MessengerRequest, MessengerResponse> {

    private final MessengerDao controller = EntityControllerFactory
            .getMessengerController();

    /**
     * Validate if messenger have all attributes for save to database.
     *
     * @param messenger messenger to validate
     *
     * @throws BadRequestException if the messenger data is null
     * @throws ServiceException if the messenger data don't have any attribute
     * required
     */
    private void validateMessenger(MessengerRequest messenger)
            throws BadRequestException, ServiceException {
        if (messenger == null) {
            throw new BadRequestException(config.getString("meesenger.is_null"));
        } else {
            ErrorMessageData emd = new ErrorMessageData();
            MessengerValidator.evaluateMessenger(messenger, emd, config);

            //verify if exists errors
            if (!emd.getMessages().isEmpty()) {
                emd.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
                throw new ServiceException(emd);
            }
        }
    }

    /**
     * Get messenger entity by messenger id provided.
     *
     * @param id messenger ID
     * @return a messenger data
     *
     * @throws BadRequestException if the messenger ID is null or less than or
     * equal to 0.
     * @throws NotFoundException if the messenger obtained is null.
     */
    private Messenger getEntity(Long id) throws BadRequestException,
            NotFoundException {
        if (id == null || id <= 0)
            throw new BadRequestException(
                    config.getString("meesenger.id_required"));

        Messenger messenger = controller.find(id);
        if (messenger == null) {
            String msg = String.format(config.getString("messenger.not_found"), id);
            throw new NotFoundException(msg);
        }
        return messenger;
    }

    @Override
    public MessengerResponse add(MessengerRequest messengerRequest) {
        validateMessenger(messengerRequest);
        Messenger messenger = controller.save(convertToEntity(messengerRequest));
        return convertToDto(messenger);
    }

    @Override
    public MessengerResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    @Override
    public List<MessengerResponse> getAll(int start, int size) {
        List<MessengerResponse> messengerDatas = new ArrayList<>();
        for (Messenger messenger : controller.findAll(start, size))
            messengerDatas.add(convertToDto(messenger));
        return messengerDatas;
    }

    @Override
    public MessengerResponse update(Long id, MessengerRequest messenger) {
        validateMessenger(messenger);
        Messenger currentMessenger = getEntity(id);

        //change person data
        currentMessenger.setIdentification(messenger.getIdentification());
        currentMessenger.setName(messenger.getName());
        currentMessenger.setLastName(messenger.getLastName());
        currentMessenger.setBirthday(messenger.getBirthday());
        currentMessenger.setGender(messenger.getGender());

        //change messenger data
        //currentMessenger.setDismissal(messenger.getDismissal());
        //currentMessenger.setGroundsDismissals(messenger.getGroundsDismissals());
        currentMessenger.setObservations(messenger.getObservations());
        currentMessenger.setPhoto(messenger.getPhoto());
        currentMessenger.setCategory(currentMessenger.getCategory());

        CurriculumVitaeData cv = messenger.getCurriculumVitae();
        CurriculumVitae currentCv = currentMessenger.getCurriculumVitae();

        //change curriculim vitae
        currentCv.setAcademicLevel(cv.getAcademicLevel());
        currentCv.setBirthCity(cv.getBirthCity());
        currentCv.setDistrict(cv.getDistrict());
        currentCv.setMilitaryCard(cv.getMilitaryCard());
        currentCv.setCivilStatus(cv.getCivilStatus());

        //change home data
        HomeData home = cv.getHome();
        currentCv.getHome().setHomeownership(home.getHomeownership());
        currentCv.getHome().setMonthlyExpenses(home.getMonthlyExpenses());
        currentCv.getHome().setSonsNumber(home.getSonsNumber());
        currentCv.getHome().setStratum(home.getStratum());

        //change social security data
        SocialSecurityData ss = cv.getSocialSecurity();
        currentCv.getSocialSecurity().setNameARL(ss.getNameARL());
        currentCv.getSocialSecurity().setNameEPS(ss.getNameEPS());
        currentCv.getSocialSecurity().setPensionEntity(ss.getPensionEntity());

        return convertToDto(controller.save(currentMessenger));
    }

    @Override
    public MessengerResponse delete(Long id) {
        MessengerResponse data = get(id);
        controller.delete(id);
        return data;
    }

    @Override
    public Messenger convertToEntity(MessengerRequest data) {
        Messenger m = new Messenger();

        //convert person data
        m.setIdentification(data.getIdentification());
        m.setName(data.getName());
        m.setLastName(data.getLastName());
        m.setGender(data.getGender());
        m.setBirthday(data.getBirthday());

        //convert messenger data
        m.setDismissal(data.getDismissal());
        m.setObservations(data.getObservations());
        m.setPhoto(data.getPhoto());
        m.setCategory(data.getCategory());

        //convert addresses if they exist
        NeighborhoodDao neiController = EntityControllerFactory.getNeighborhoodController();
        if (data.getAddresses() != null) {
            for (AddressRequest a : data.getAddresses()) {
                Neighborhood nei = neiController.find(a.getNeighborhood());
                m.addAddress(new Address(a.getTag(),
                        a.getResidentialAddress(), nei));
            }
        }

        //convert phones if they exist
        if (data.getPhones() != null)
            for (PhoneRequest p : data.getPhones())
                m.addPhone(new Phone(p.getTag(), p.getNumber(), p.getPhoneType()));

        //convert emails if they exist
        if (data.getEmails() != null)
            for (EmailRequest email : data.getEmails())
                m.addEmail(new Email(email.getAddress()));

        //References need to be converted if they exist
        //convert curriculum vitae
        CurriculumVitaeRequest cvr = data.getCurriculumVitae();
        CurriculumVitae cv = new CurriculumVitae();
        cv.setCivilStatus(cvr.getCivilStatus());
        cv.setBirthCity(cvr.getBirthCity());
        cv.setAcademicLevel(cvr.getAcademicLevel());
        cv.setMilitaryCard(cvr.getMilitaryCard());
        cv.setDistrict(cvr.getDistrict());

        //convert social security
        SocialSecurityData ssd = cvr.getSocialSecurity();
        SocialSecurity ss = new SocialSecurity(ssd.getNameEPS(),
                ssd.getNameARL(), ssd.getPensionEntity());

        //convert home
        HomeData hd = cvr.getHome();
        Home home = new Home(hd.getStratum(), hd.getSonsNumber(),
                hd.getHomeownership(), hd.getMonthlyExpenses());

        cv.setSocialSecurity(ss);
        cv.setHome(home);
        m.setCurriculumVitae(cv);

        return m;
    }

    @Override
    public MessengerResponse convertToDto(Messenger m) {
        //convert list of addresses
        List<AddressResponse> aResponse = new ArrayList<>();
        if (m.getAddresses() != null) {
            for (Address a : m.getAddresses()) {
                Neighborhood nei = a.getNeighborhood();
                NeighborhoodResponse neiResponse
                        = new NeighborhoodResponse(nei.getId(), nei.getName());
                aResponse.add(new AddressResponse(
                        a.getTag(),
                        a.getResidentialAddress(),
                        neiResponse));
            }
        }

        //convert list of emails
        List<EmailResponse> eResponse = new ArrayList<>();
        if (m.getEmails() != null) {
            for (Email email : m.getEmails()) {
                eResponse.add(new EmailResponse(email.getAddress(),
                        email.isConfirmed()));
            }
        }

        //convert list of phones
        List<PhoneResponse> pResponse = new ArrayList<>();
        if (m.getPhones() != null) {
            for (Phone p : m.getPhones()) {
                pResponse.add(new PhoneResponse(p.getTag(), p.getNumber(),
                        p.getType(), p.isConfirmed()));
            }
        }

        CurriculumVitae cv = m.getCurriculumVitae();

        //convert social security
        SocialSecurity ss = cv.getSocialSecurity();
        SocialSecurityData ssd = new SocialSecurityData(ss.getNameEPS(),
                ss.getNameARL(), ss.getPensionEntity());

        //convert home
        Home h = cv.getHome();
        HomeData hd = new HomeData(h.getStratum(), h.getSonsNumber(),
                h.getHomeownership(), h.getMonthlyExpenses());

        //convert curriculum vitae
        CurriculumVitaeResponse cvr = new CurriculumVitaeResponse();
        cvr.setAcademicLevel(cv.getAcademicLevel());
        cvr.setBirthCity(cv.getBirthCity());
        cvr.setCivilStatus(cv.getCivilStatus());
        cvr.setDistrict(cv.getDistrict());
        cvr.setHome(hd);
        cvr.setMilitaryCard(cv.getMilitaryCard());
        cvr.setSocialSecurity(ssd);

        //convert to messenger response
        MessengerResponse response = new MessengerResponse(m.getId(), aResponse,
                eResponse, pResponse, cvr, m.getUser().getId(),
                m.getIdentification(), m.getName(), m.getLastName(),
                m.getGender(), m.getBirthday(), m.isDismissal(),
                m.getObservations(), m.getPhoto(), m.getCategory());
        return response;
    }

}
