package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.PersonDao;
import com.nativapps.arpia.database.dao.PhoneDao;
import com.nativapps.arpia.database.entity.Person;
import com.nativapps.arpia.database.entity.Phone;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.model.dto.PhoneResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.util.TextUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class PhoneServiceImpl extends GenericService implements PhoneService,
        DtoConverter<Phone, PhoneRequest, PhoneResponse> {

    private final PersonDao personDao = EntityControllerFactory
            .getPersonController();

    private final PhoneDao phoneDao = EntityControllerFactory
            .getPhoneController();
    
    @Override
    public PhoneResponse add(Long personId, PhoneRequest data) {
        if (personId == null || personId <= 0)
            throw new BadRequestException(config.getString("person.id_required"));
        if (data == null)
            throw new BadRequestException(config.getString("phone.is_null"));
        else {
            ErrorMessageData errors = new ErrorMessageData();
            if (data.getTag() == null)
                errors.addMessage(config.getString("phone.tag_required"));
            if (data.getNumber() == null)
                errors.addMessage(config.getString("phone.number_required"));
            else {
                if (!TextUtil.isPhone(data.getNumber()))
                    errors.addMessage(String.format(config.getString("phone.is_valid"), data.getNumber()));
                else if (phoneDao.exists(data.getNumber()))
                    errors.addMessage(String.format(config.getString("phone.n_exists"), data.getNumber()));
                if (!errors.getMessages().isEmpty()) {
                    errors.setStatusCode(Response.Status.BAD_REQUEST
                            .getStatusCode());
                    throw new ServiceException(errors);
                }
            }
        }

        Person person = personDao.find(personId);

        if (person == null) {
            throw new NotFoundException(String.format(config
                    .getString("person.not_found"), personId));
        }

        Phone phone = convertToEntity(data);
        phone.setOwner(person);

        return convertToDto(phoneDao.save(phone));
    }

    @Override
    public PhoneResponse get(Integer index, Long personId) {
        return convertToDto(getEntity(personId, index));
    }

    /**
     * Search phone entity from the id provided
     *
     * @param personId ID of the person
     * @param index phone position
     * @return searched phone
     */
    private Phone getEntity(Long personId, Integer index) {
        if (index == null || index <= 0)
            throw new BadRequestException(config.getString("phone.index_required"));

        List<Phone> phones = phoneDao.getAllByPersonId(personId);
        if (index <= phones.size())
            return phones.get(index - 1);
        throw new NotFoundException(String.format(config.getString("phone.not_found"), 
                personId, index));
    }

    @Override
    public List<PhoneResponse> getAll(Long personId) {
        if (personId == null || personId <= 0)
            throw new BadRequestException(config.getString("person.id_required"));

        if (personDao.find(personId) == null)
            throw new NotFoundException(String.format(config
                    .getString("person.not_found"), personId));

        List<Phone> phones = phoneDao.getAllByPersonId(personId);
        List<PhoneResponse> data = new ArrayList<>();

        for (Phone phone : phones) {
            data.add(convertToDto(phone));
        }

        return data;
    }

    @Override
    public PhoneResponse delete(Integer index, Long personId) {
        PhoneResponse data = get(index, personId);
        phoneDao.delete(getEntity(personId, index).getId());
        return data;
    }

    @Override
    public PhoneResponse convertToDto(Phone entity) {
        return new PhoneResponse(entity.getTag(), entity.getNumber(),
                entity.getType(), entity.isConfirmed());
    }

    @Override
    public Phone convertToEntity(PhoneRequest data) {
        return new Phone(data.getTag(), data.getNumber(), data.getPhoneType());
    }
}
