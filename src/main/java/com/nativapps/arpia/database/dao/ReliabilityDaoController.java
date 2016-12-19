package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Reliability;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.0
 */
public class ReliabilityDaoController extends EntityDao<Reliability, Long>
        implements ReliabilityDao {

    private static final ReliabilityDaoController INSTANCE
            = new ReliabilityDaoController();

    private ReliabilityDaoController() {
        super(Reliability.class);
    }

    public static ReliabilityDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Reliability findByMessengerId(Long messengerId) {
        return executeNamedQuery("reliability.findByMessengerId",
                new Parameter("id", messengerId));
    }

}
