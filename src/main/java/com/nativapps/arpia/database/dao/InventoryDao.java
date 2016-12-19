package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Inventory;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * 
 * @version 1.1
 */
public interface InventoryDao extends DataAccessObject<Inventory, Long> {

    Inventory findByName(String name);

}
