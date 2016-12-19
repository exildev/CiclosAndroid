package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Gender;
import com.nativapps.arpia.database.entity.Particular;
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
public class ParticularDaoController extends EntityDao<Particular, Long>
        implements ParticularDao {

    private static final Logger LOG = Logger.getLogger(ParticularDaoController.class.getName());

    private static final ParticularDaoController INSTANCE
            = new ParticularDaoController();

    private ParticularDaoController() {
        super(Particular.class);
    }

    public static ParticularDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Particular> findAll(int start, int size, String data,
            String orderBy, OrderType orderType, Gender gender) {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Particular> query = cb
                    .createQuery(Particular.class);

            Root<Particular> p = query.from(Particular.class);
            query.select(p);

            Predicate pdata = null;
            if (data != null) {
                Path<String> pathIdentification = p.get("identification");
                Path<String> pathName = p.get("name");
                Path<String> pathLastName = p.get("lastName");

                Predicate p1 = cb.like(pathIdentification, "%" + data + "%");
                Predicate p2 = cb.like(pathName, "%" + data + "%");
                Predicate p3 = cb.like(pathLastName, "%" + data + "%");

                pdata = cb.or(p1, p2, p3);
            }

            if (pdata != null && gender != null)
                query.where(pdata, cb.and(cb.equal(p
                        .get("gender"), gender)));
            else if (gender != null)
                query.where(cb.equal(p.get("gender"), gender));

            if (orderBy != null) {
                Order order;
                if (orderType == null || orderType == OrderType.ASC)
                    order = cb.asc(p.get(orderBy));
                else
                    order = cb.desc(p.get(orderBy));

                query.orderBy(order);
            }

            TypedQuery<Particular> typedQuery = entityManager.createQuery(query);

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
    public Particular findByCustomerId(Long id) {
        return executeNamedQuery("particular.findByCustomerId", 
                new Parameter("customerId", id));
    }

}
