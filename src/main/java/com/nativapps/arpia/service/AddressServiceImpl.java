package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.AddressDao;
import com.nativapps.arpia.database.dao.EstablishmentDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.dao.PersonDao;
import com.nativapps.arpia.database.entity.Address;
import com.nativapps.arpia.database.entity.Establishment;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.database.entity.Person;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
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
public class AddressServiceImpl extends GenericService implements AddressService,
        DtoConverter<Address, AddressRequest, AddressResponse> {

    private final AddressDao addressDao = EntityControllerFactory
            .getAddressController();

    private final PersonDao personDao = EntityControllerFactory
            .getPersonController();

    private final EstablishmentDao establishmentDao = EntityControllerFactory
            .getEstablishmentController();

    private final NeighborhoodDao neighborhoodDao = EntityControllerFactory
            .getNeighborhoodController();

    @Override
    public AddressResponse add(Long id, Owner type,
            AddressRequest data) {
        ErrorMessageData errors = new ErrorMessageData();
        if (id == null || id <= 0) {
            errors.addMessage(config.getString("address.owner_id_required"));
        }
        if (data == null) {
            errors.addMessage(config.getString("address.is_null"));
        } else {
            if (data.getTag() == null) {
                errors.addMessage(config.getString("address.tag_required"));
            }
            if (data.getResidentialAddress() == null) {
                errors.addMessage("The residential address is required");
            }
            if (data.getNeighborhood() == null) {
                errors.addMessage(config.getString("address.neighborhood_required"));
            }
        }

        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }

        Address address = convertToEntity(data);
        switch (type) {
            case PERSON:
                Person person = personDao.find(id);
                if (person == null) {
                    throw new NotFoundException(String.format(config
                            .getString("person.not_found"), id));
                }
                address.setPerson(person);
                break;
            case ESTABLISHMENT:
                Establishment establishment = establishmentDao.find(id);
                if (establishment == null) {
                    throw new NotFoundException(String.format(config
                            .getString("establishment.not_found"), id));
                }
                address.setEstablishment(establishment);
                break;
        }

        return convertToDto(addressDao.save(address));
    }

    @Override
    public List<AddressResponse> getAll(Long id, Owner type) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("address.owner_id_required"));

        List<AddressResponse> data = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        switch (type) {
            case PERSON:
                if (personDao.find(id) == null) {
                    throw new NotFoundException(String.format(config
                            .getString("person.not_found"), id));
                }
                addresses = addressDao.getAllByPersonId(id);
                break;
            case ESTABLISHMENT:
                if (establishmentDao.find(id) == null) {
                    throw new NotFoundException(String.format(config
                            .getString("establishment.not_found"), id));
                }
                addresses = addressDao.getAllByEstablishmentId(id);
                break;
        }
        for (Address address : addresses) {
            data.add(convertToDto(address));
        }

        return data;
    }

    @Override
    public AddressResponse get(Long id, Long ownerId, Owner type) {
        return convertToDto(getEntity(id, ownerId, type));
    }

    /**
     *
     * @param id Id of the address
     * @param ownerId Id of the owner
     * @param type Owner type
     * @return searched address
     */
    public Address getEntity(Long id, Long ownerId, Owner type) {
        ErrorMessageData errors = new ErrorMessageData();
        if (id == null || id <= 0)
            errors.addMessage(config.getString("address.id_required"));
        if (ownerId == null || ownerId <= 0)
            errors.addMessage(config.getString("address.owner_id_required"));

        if (!errors.getMessages().isEmpty()) {
            errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
            throw new ServiceException(errors);
        }

        Address response = null;
        switch (type) {
            case PERSON:
                if (personDao.find(ownerId) == null)
                    throw new NotFoundException(String.format(config
                            .getString("person.not_found"), ownerId));
                response = addressDao.getByPersonId(id, ownerId);
                break;
            case ESTABLISHMENT:
                if (establishmentDao.find(ownerId) == null)
                    throw new NotFoundException(String.format(config
                            .getString("establishment.not_found"), ownerId));
                response = addressDao.getByEstablishmentId(id, ownerId);
                break;
        }

        if (response == null)
            throw new NotFoundException(String.format(config
                    .getString("address.not_found"), ownerId, id));

        return response;
    }

    @Override
    public AddressResponse update(Long id, Long ownerId, Owner type,
            AddressRequest data) {
        Address address = getEntity(id, ownerId, type);
        if (!TextUtil.isEmpty(data.getTag()) && !data.getTag()
                .equalsIgnoreCase(address.getTag())) {
            address.setTag(data.getTag());
        }
        if (!TextUtil.isEmpty(data.getResidentialAddress()) && !data
                .getResidentialAddress().equalsIgnoreCase(address
                        .getResidentialAddress())) {
            address.setResidentialAddress(data.getResidentialAddress());
        }
        if (data.getNeighborhood() != null && data.getNeighborhood()
                != address.getNeighborhood().getId()) {
            Neighborhood neighborhood = neighborhoodDao.find(data
                    .getNeighborhood());
            if (neighborhood == null)
                throw new NotFoundException(String.format(config
                        .getString("address.neighborhood_not_found"), data
                        .getNeighborhood()));
            address.setNeighborhood(neighborhood);
        }

        return convertToDto(addressDao.save(address));
    }

    @Override
    public AddressResponse delete(Long id, Long ownerId, Owner type) {
        AddressResponse data = get(id, ownerId, type);
        addressDao.delete(id);
        return data;
    }

    @Override
    public Address convertToEntity(AddressRequest data) {
        Neighborhood neighborhood = neighborhoodDao.find(data.getNeighborhood());
        if (neighborhood == null)
            throw new NotFoundException(String.format(config
                    .getString("address.neighborhood_not_found"), data
                    .getNeighborhood()));

        return new Address(data.getTag(), data.getResidentialAddress(),
                neighborhood);
    }

    @Override
    public AddressResponse convertToDto(Address entity) {
        return new AddressResponse(entity.getId(), entity.getTag(),
                entity.getResidentialAddress(), new NeighborhoodResponse(entity
                .getNeighborhood().getId(), entity.getNeighborhood()
                        .getName()));
    }
}
