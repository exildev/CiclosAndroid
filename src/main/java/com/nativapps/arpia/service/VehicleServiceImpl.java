package com.nativapps.arpia.service;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.MessengerDao;
import com.nativapps.arpia.database.dao.VehicleDao;
import com.nativapps.arpia.database.entity.Messenger;
import com.nativapps.arpia.database.entity.Vehicle;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.model.dto.VehicleRequest;
import com.nativapps.arpia.model.dto.VehicleResponse;
import com.nativapps.arpia.service.exception.ServiceException;
import com.nativapps.arpia.service.validator.VehicleValidator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class VehicleServiceImpl extends GenericService
        implements VehicleService,
        DtoConverter<Vehicle, VehicleRequest, VehicleResponse> {

    private final VehicleDao vehicleController = EntityControllerFactory
            .getVehicleController();

    private final MessengerDao messengerController = EntityControllerFactory
            .getMessengerController();

    @Override
    public VehicleResponse add(Long messengerId, VehicleRequest data) {
        validateVehicle(data);
        Messenger messenger = getMessengerEntity(messengerId);
        Vehicle vehicle = convertToEntity(data);
        vehicle.setMessenger(messenger);
        return convertToDto(vehicleController.save(vehicle));
    }

    @Override
    public VehicleResponse get(Long id) {
        return convertToDto(getEntity(id));
    }

    @Override
    public List<VehicleResponse> getAll(Long messengerId) {
        List<VehicleResponse> vehiclesDatas = new ArrayList<>();

        for (Vehicle vehicle
                : vehicleController.findAllByMessengerId(messengerId)) {
            vehiclesDatas.add(convertToDto(vehicle));
        }
        return vehiclesDatas;
    }

    @Override
    public List<VehicleResponse> getAll(int start, int size) {
        if (start < 0)
            throw new BadRequestException(config.getString("pagination.start"));
        if (size <= 0)
            throw new BadRequestException(config.getString("pagination.size"));

        List<VehicleResponse> vehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleController.findAll(start, size)) {
            vehicles.add(convertToDto(vehicle));
        }
        return vehicles;
    }

    @Override
    public VehicleResponse update(Long id, VehicleRequest data) {
        Vehicle currentVehicle = getEntity(id);
        validateVehicle(data);
        Vehicle vehicle = convertToEntity(data);

        currentVehicle.setOwnership(vehicle.getOwnership());
        currentVehicle.setCondition(vehicle.getCondition());
        currentVehicle.setTrademark(vehicle.getTrademark());
        currentVehicle.setModel(vehicle.getModel());
        currentVehicle.setColor(vehicle.getColor());
        currentVehicle.setLicensePlate(vehicle.getLicensePlate());
        currentVehicle.setSoatExpiration(vehicle.getSoatExpiration());
        currentVehicle.setTecnomecanicaExpiration(vehicle.
                getTecnomecanicaExpiration());

        return convertToDto(vehicleController.save(currentVehicle));
    }

    @Override
    public VehicleResponse delete(Long id) {
        Vehicle vehicle = getEntity(id);
        vehicleController.delete(vehicle.getId());
        return convertToDto(vehicle);
    }

    @Override
    public Vehicle convertToEntity(VehicleRequest data) {
        return new Vehicle(data.getOwnership(), data.getVehicleCondition(),
                data.getTrademark(), data.getModel(), data.getColor(),
                data.getLicensePlate(), data.getSoatExpiration(),
                data.getTecnomecanicaExpiration());
    }

    @Override
    public VehicleResponse convertToDto(Vehicle entity) {
        return new VehicleResponse(entity.getId(), entity.getOwnership(),
                entity.getCondition(), entity.getTrademark(), entity.getModel(),
                entity.getColor(), entity.getLicensePlate(),
                entity.getTecnomecanicaExpiration(), entity.getSoatExpiration());
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
     * Validate if the data has the required structure for save to database.
     *
     * @param vehicle
     *
     * @throws BadRequestException if the vehicle data is null
     * @throws ServiceException if the vehicle data don't have any attribute
     * required
     */
    private void validateVehicle(VehicleRequest vehicle)
            throws BadRequestException, ServiceException {
        if (vehicle == null)
            throw new BadRequestException(
                    config.getString("vehicle.required"));
        else {
            ErrorMessageData errors = new ErrorMessageData();
            VehicleValidator.evaluateVehicle(vehicle, errors, config);

            if (!errors.getMessages().isEmpty()) {
                errors.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
                throw new ServiceException(errors);
            }
        }
    }

    /**
     * Get vehicle corresponding to vehicle id provided.
     *
     * @param id
     * @return vehicle searched
     *
     * @throws BadRequestException if the vehicle data is null
     * @throws NotFoundException if the vehicle is was not found
     */
    private Vehicle getEntity(Long id) {
        if (id == null || id <= 0)
            throw new BadRequestException(
                    config.getString("vehicle.index_required"));

        Vehicle vehicle = vehicleController.find(id);
        if (vehicle == null) {
            String msg
                    = String.format(config.getString("vehicle.index_not_found"), id);
            throw new NotFoundException(msg);
        }
        return vehicle;
    }
}
