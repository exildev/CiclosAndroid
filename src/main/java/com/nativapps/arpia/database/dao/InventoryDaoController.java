package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Inventory;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1
 */
public class InventoryDaoController extends EntityDao<Inventory, Long>
        implements InventoryDao {

    private static final InventoryDaoController INSTANCE
            = new InventoryDaoController();

    private InventoryDaoController() {
        super(Inventory.class);
    }

    public static InventoryDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Inventory findByName(String name) {
        return executeNamedQuery("inventory.findByName",
                new Parameter("name", name));
    }

}
