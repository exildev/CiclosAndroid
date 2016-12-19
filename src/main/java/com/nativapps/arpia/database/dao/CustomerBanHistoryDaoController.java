package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.CustomerBanHistory;
import com.nativapps.arpia.database.exception.DatabaseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CustomerBanHistoryDaoController
        extends EntityDao<CustomerBanHistory, Long>
        implements CustomerBanHistoryDao {

    private static final CustomerBanHistoryDaoController INSTANCE
            = new CustomerBanHistoryDaoController();

    private CustomerBanHistoryDaoController() {
        super(CustomerBanHistory.class);
    }

    public static CustomerBanHistoryDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CustomerBanHistory> findAllByCustomerId(int start, int size,
            Long customerId) {
        return executeNamedQueryForList("customerHistory.findAllByCustomerId",
                start, size, new Parameter("customerId", customerId));
    }

    @Override
    public CustomerBanHistory findLastBanByCustomerId(Long customerId) {
        EntityManager manager = getEntityManager();

        try {
            TypedQuery<CustomerBanHistory> query = manager
                    .createNamedQuery("customerHistory.findAllByCustomerId",
                            CustomerBanHistory.class)
                    .setParameter("customerId", customerId);
            
            return query.setMaxResults(1).getSingleResult();
        } catch (Exception ex) {
            throw new DatabaseException(ex.getCause().getMessage(), ex);
        } finally {
            if (manager != null)
                manager.close();
        }
    }
}
