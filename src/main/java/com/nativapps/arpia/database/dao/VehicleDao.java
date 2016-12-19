package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Vehicle;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public interface VehicleDao extends DataAccessObject<Vehicle, Long> {

    /**
     * Gets all vehicle information by messenger id provided.
     *
     * @param messengerId
     * @return vehicle list from messenger
     */
    public List<Vehicle> findAllByMessengerId(Long messengerId);
    
    
    /**
     * Returns vehicle list paginated
     * 
     * @param start Initial index
     * @param size List size
     * @return vehicle list
     */
    List<Vehicle> findAll(int start, int size);
}
