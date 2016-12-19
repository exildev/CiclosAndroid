package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.EstablishmentDao;
import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.entity.Address;
import com.nativapps.arpia.database.entity.Establishment;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.model.OrderType;
import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.EstablishmentRequest;
import com.nativapps.arpia.model.dto.EstablishmentResponse;
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
public class EstablishmentServiceImpl extends GenericService implements EstablishmentService,
        DtoConverter<Establishment, EstablishmentRequest, EstablishmentResponse> {

    private final EstablishmentDao establishmentDao = EntityControllerFactory
            .getEstablishmentController();

    private final NeighborhoodDao neighborhoodDao = EntityControllerFactory
            .getNeighborhoodController();

    @Override
    public EstablishmentResponse add(EstablishmentRequest data) {
        if (data == null) {
            throw new BadRequestException(config
                    .getString("establishment.is_null"));
        } else {
            ErrorMessageData errors = new ErrorMessageData();
            if (data.getName() == null)
                errors.addMessage(config
                        .getString("establishment.name_required"));
            if (data.getNit() == null)
                errors.addMessage(config
                        .getString("establishment.nit_required"));

            if (data.getAddresses() != null) {
                for (AddressRequest address : data.getAddresses()) {
                    if (address.getNeighborhood() == null)
                        errors.addMessage(config
                                .getString("address.neighborhood_null"));
                    if (TextUtil.isEmpty(address.getResidentialAddress()))
                        errors.addMessage(config
                                .getString("address.resid_address_nul"));
                    if (TextUtil.isEmpty(address.getTag()))
                        errors.addMessage(config.getString("address.tag_null"));
                }
            } else
                errors.addMessage(config.getString("addres.at_least"));

            if (!errors.getMessages().isEmpty()) {
                errors.setStatusCode(Response.Status.BAD_REQUEST
                        .getStatusCode());
                throw new ServiceException(errors);
            }
        }

        return convertToDto(establishmentDao.save(convertToEntity(data)));
    }

    @Override
    public EstablishmentResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    /**
     * Returns the establishment entity by id provided
     *
     * @param id Establishment id
     * @return Searched entity
     */
    public Establishment getEntity(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(config
                    .getString("establishment.id_required"));

        Establishment establishment = establishmentDao.findByCustomerId(id);

        if (establishment == null)
            throw new NotFoundException(String.format(config
                    .getString("establishment.not_found"), id));

        return establishment;
    }

    @Override
    public List<EstablishmentResponse> getAll(int start, int size, String search,
            String orderBy, OrderType orderType) {
        if (start < 0)
            throw new BadRequestException(config.getString("pagination.start"));
        if (size <= 0)
            throw new BadRequestException(config.getString("pagination.size"));

        List<EstablishmentResponse> data = new ArrayList<>();
        List<Establishment> result = establishmentDao.findAll(start, size,
                search, orderBy, orderType);
        for (Establishment establishment : result) {
            data.add(convertToDto(establishment));
        }

        return data;
    }

    @Override
    public EstablishmentResponse update(Long id,
            EstablishmentRequest data) {

        if (data == null)
            throw new BadRequestException(config
                    .getString("establishment.is_null"));

        Establishment entity = getEntity(id);

        if (!TextUtil.isEmpty(data.getNit()) && !data.getNit()
                .equalsIgnoreCase(entity.getNit()))
            entity.setNit(data.getNit());
        if (!TextUtil.isEmpty(data.getName()) && !data.getName()
                .equalsIgnoreCase(entity.getName()))
            entity.setName(data.getName());
        if (!TextUtil.isEmpty(data.getObservations()) && !data.getObservations()
                .equalsIgnoreCase(entity.getCustomerData().getObservations()))
            entity.getCustomerData().setObservations(data.getObservations());

        return convertToDto(establishmentDao.save(entity));
    }

    @Override
    public EstablishmentResponse delete(Long id) {
        EstablishmentResponse data = get(id);
        establishmentDao.delete(id);
        return data;
    }

    @Override
    public Establishment convertToEntity(EstablishmentRequest data) {
        Establishment establishment = new Establishment(data.getNit(),
                data.getName(), data.getObservations());

        for (AddressRequest adata : data.getAddresses()) {
            Neighborhood neighborhood = neighborhoodDao.find(adata
                    .getNeighborhood());
            if (neighborhood == null)
                throw new NotFoundException(String.format(config
                        .getString("address.neighborhood_not_found"), adata
                        .getNeighborhood()));
            establishment.addAddress(new Address(adata.getTag(),
                    adata.getResidentialAddress(), neighborhood));
        }

        return establishment;
    }

    @Override
    public EstablishmentResponse convertToDto(Establishment entity) {
        EstablishmentResponse data
                = new EstablishmentResponse(entity.getCustomerData().getId(),
                        entity.getNit(), entity.getName(),
                        entity.getCustomerData().getObservations());

        for (Address address : entity.getAddresses()) {
            data.getAddresses().add(new AddressResponse(address.getId(),
                    address.getTag(), address.getResidentialAddress(),
                    new NeighborhoodResponse(address.getNeighborhood().getId(),
                            address.getNeighborhood().getName())));
        }

        return data;
    }
}
