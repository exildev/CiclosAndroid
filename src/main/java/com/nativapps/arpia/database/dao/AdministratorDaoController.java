package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Administrator;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class AdministratorDaoController extends EntityDao<Administrator, Long>
        implements AdministratorDao {
    
    private static final AdministratorDaoController INSTANCE 
            = new AdministratorDaoController();

    private AdministratorDaoController() {
        super(Administrator.class);
    }

    public static AdministratorDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Administrator getByEstablishmentId(Long id, Long establishmentId) {
        return executeNamedQuery("findByEstablishmentId",
                new Parameter("establishmentId", establishmentId),
                new Parameter("administratorId", id));
    }

    @Override
    public List<Administrator> getAllByEstablishmentId(Long establishmentId) {
        return executeNamedQueryForList("findAllByEstablishmentId",
                new Parameter("establishmentId", establishmentId));
    }

}
