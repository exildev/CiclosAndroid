package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Administrator;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public interface AdministratorDao extends DataAccessObject<Administrator, Long> {

    /**
     * Returns a administrator entity by his own ID and his establishment ID
     *
     * @param id Administrator's ID
     * @param establishmentId Establishment's ID
     * @return searched entity
     */
    Administrator getByEstablishmentId(Long id, Long establishmentId);

    /**
     * Returns a list of administrators entity by his estatablishment ID
     *
     * @param establishmentId Establishment's ID
     * @return List of administrator
     */
    List<Administrator> getAllByEstablishmentId(Long establishmentId);
}
