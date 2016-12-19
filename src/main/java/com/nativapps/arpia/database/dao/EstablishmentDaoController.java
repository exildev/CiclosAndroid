package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Establishment;
import com.nativapps.arpia.database.exception.DatabaseException;
import com.nativapps.arpia.model.OrderType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class EstablishmentDaoController extends EntityDao<Establishment, Long>
        implements EstablishmentDao {

    private static final Logger LOG = Logger
            .getLogger(EstablishmentDaoController.class.getName());
    
    private static final EstablishmentDaoController INSTANCE
            = new EstablishmentDaoController();

    private EstablishmentDaoController() {
        super(Establishment.class);
    }

    public static EstablishmentDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Establishment> findAll(int start, int size, String data,
            String orderBy, OrderType orderType) {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Establishment> query = cb
                    .createQuery(Establishment.class);

            Root<Establishment> e = query.from(Establishment.class);
            query.select(e);

            if (data != null) {
                Path<String> pathNit = e.get("nit");
                Path<String> pathName = e.get("name");

                Predicate p1 = cb.like(pathNit, "%" + data + "%");
                Predicate p2 = cb.like(pathName, "%" + data + "%");

                query.where(cb.or(p1, p2));
            }

            if (orderBy != null) {
                Order order;
                if (orderType == null || orderType == OrderType.ASC)
                    order = cb.asc(e.get(orderBy));
                else
                    order = cb.desc(e.get(orderBy));

                query.orderBy(order);
            }

            TypedQuery<Establishment> typedQuery = entityManager
                    .createQuery(query);

            return size == 0 ? typedQuery.getResultList() : typedQuery
                    .setMaxResults(size).setFirstResult(start).getResultList();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getCause().getMessage(), ex);
            throw new DatabaseException(ex.getCause().getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    @Override
    public Establishment findByCustomerId(Long id) {
        return executeNamedQuery("establishment.findByCustomerId", 
                new Parameter("customerId", id));
    }
}
