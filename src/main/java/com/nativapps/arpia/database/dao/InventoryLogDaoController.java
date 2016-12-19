package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.InventoryLog;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class InventoryLogDaoController extends EntityDao<InventoryLog, Long>
        implements InventoryLogDao {

    private static final InventoryLogDaoController INSTANCE
            = new InventoryLogDaoController();

    private InventoryLogDaoController() {
        super(InventoryLog.class);
    }

    public static InventoryLogDaoController getInstance() {
        return INSTANCE;
    }

}
