package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Vehicle;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class VehicleDaoController extends EntityDao<Vehicle, Long>
        implements VehicleDao {

    private static final VehicleDaoController INSTANCE
            = new VehicleDaoController();

    private VehicleDaoController() {
        super(Vehicle.class);
    }

    public static VehicleDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Vehicle> findAllByMessengerId(Long messengerId) {
        return executeNamedQueryForList("vehicle.getAllByMessengerId",
                new Parameter("messengerId", messengerId));
    }

    @Override
    public List<Vehicle> findAll(int start, int size) {
        return executeNamedQueryForList("vehicle.findAll", start, size);
    }
}
