package com.nativapps.arpia.service;

import java.io.Serializable;

/**
 * The <strong>DtoConverter</strong> interface provided methods for
 * convert entities to DTO(Data Transfer Object) and DTO to entities.
 *
 * @param <E> Entity type
 * @param <RQ> Request entity
 * @param <RS> Response entity
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface DtoConverter<E extends Serializable, RQ, RS> {

    /**
     * Convert Data Transfer Object Request to entity
     *
     * @param data Data Transfer Object Request provided
     * @return entity object
     */
    E convertToEntity(RQ data);

    /**
     * Convert entity to Data Transfer Object SimpleResponse
     *
     * @param entity Entity provided
     * @return Data Transfer Object SimpleResponse
     */
    RS convertToDto(E entity);
}
