package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Messenger;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MessengerDaoController extends EntityDao<Messenger, Long>
        implements MessengerDao {

    private static final Logger LOG = Logger.getLogger(MessengerDaoController.class.getName());

    private static final MessengerDaoController INSTANCE
            = new MessengerDaoController();

    private MessengerDaoController() {
        super(Messenger.class);
    }

    public static MessengerDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Messenger> findAll(int start, int size) {
        return executeNamedQueryForList("messenger.findAll", start, size);
    }
}
