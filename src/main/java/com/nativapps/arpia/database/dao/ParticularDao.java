package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Gender;
import com.nativapps.arpia.database.entity.Particular;
import com.nativapps.arpia.model.OrderType;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface ParticularDao extends DataAccessObject<Particular, Long> {
    
    /**
     * Return a filter particular list by string 
     * 
     * @param start Initial index
     * @param size List size
     * @param data Filter particular list
     * @param orderBy Atributte to order
     * @param orderType Order type to list
     * @param gender Filter gender
     * @return Filter list
     */
    List<Particular> findAll(int start, int size, String data, String orderBy, 
            OrderType orderType, Gender gender);
    
    /**
     * Returns a particular entity by customer ID
     * 
     * @param id Customer ID
     * @return Particular entity
     */
    Particular findByCustomerId(Long id);
}
