package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.NeighborhoodRequest;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface NeighborhoodService extends Service {

    /**
     * Returns neighborhood list paginated
     *
     * @param start Initial index
     * @param size List size
     * @return Neighborhood list
     */
    List<NeighborhoodResponse> getAll(int start, int size);

    /**
     * Returns a specific neighborhood by ID
     *
     * @param id Neighborhood ID
     * @return Searched entity
     */
    NeighborhoodResponse get(Long id);

    /**
     * Create a new neighborhood
     *
     * @param data Neighborhood information
     * @return Neighborhood created
     */
    NeighborhoodResponse add(NeighborhoodRequest data);

    /**
     * Update a neighborhood
     *
     * @param id Neighborhood ID
     * @param data Neighborhood information
     * @return Updated neighborhood
     */
    NeighborhoodResponse update(Long id, NeighborhoodRequest data);

    /**
     * Delete a neigborhood
     *
     * @param id Neighborhood ID
     * @return Deleted neighborhood
     */
    NeighborhoodResponse delete(Long id);
    
    /**
     * Returns a specific neighborhood by name
     * 
     * @param name Neighborhood name
     * @return Searched neighborhood
     */
    NeighborhoodResponse getByName(String name);
}
