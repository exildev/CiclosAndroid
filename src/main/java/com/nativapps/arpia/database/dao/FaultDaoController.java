package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Fault;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class FaultDaoController extends EntityDao<Fault, Long>
        implements FaultDao {

    private static final FaultDaoController INSTANCE
            = new FaultDaoController();

    private FaultDaoController() {
        super(Fault.class);
    }

    public static FaultDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Fault> getAllByMessengerId(Long messengerId) {
        return executeNamedQueryForList("fault.getAllByMessengerId",
                new Parameter("messengerId", messengerId));
    }
}
