package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Fault;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface FaultDao extends DataAccessObject<Fault, Long> {

    public List<Fault> getAllByMessengerId(Long messengerId);
}
