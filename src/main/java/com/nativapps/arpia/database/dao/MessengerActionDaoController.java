package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.MessengerAction;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.0
 */
public class MessengerActionDaoController extends EntityDao<MessengerAction, Long>
        implements MessengerActionDao {

    private static final MessengerActionDaoController INSTANCE
            = new MessengerActionDaoController();

    private MessengerActionDaoController() {
        super(MessengerAction.class);
    }

    public static MessengerActionDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<MessengerAction> findAllByMessengerId(Long messengerId) {
        return executeNamedQueryForList("messengerAction.getAllByMessengerId",
                new Parameter("messengerId", messengerId));
    }

    @Override
    public List<MessengerAction> findAll(int start, int size, Long messengerId) {
        return executeNamedQueryForList("messengerAction.findAll", start, size,
                new Parameter("messengerId", messengerId));
    }

}
