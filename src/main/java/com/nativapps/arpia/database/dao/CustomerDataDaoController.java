package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.CustomerData;
import java.util.List;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CustomerDataDaoController extends EntityDao<CustomerData, Long>
        implements CustomerDataDao {

    private static final CustomerDataDaoController INSTANCE
            = new CustomerDataDaoController();

    private CustomerDataDaoController() {
        super(CustomerData.class);
    }

    public static CustomerDataDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CustomerData> findAllPaginated(int start, int size) {
        return executeNamedQueryForList("customerData.findAll", start, size);
    }
}
