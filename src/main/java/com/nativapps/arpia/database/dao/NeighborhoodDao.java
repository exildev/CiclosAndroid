package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Neighborhood;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface NeighborhoodDao extends DataAccessObject<Neighborhood, Long> {

    /**
     * Returns neighborhood list paginated
     * 
     * @param start Initial index
     * @param size List size
     * @return Neighborhood list
     */
    List<Neighborhood> findAll(int start, int size);
    
    /**
     * Search a neighborhood by his name
     * 
     * @param name Neighborhood name
     * @return Searched neighborhood
     */
    Neighborhood findByName(String name);
}
