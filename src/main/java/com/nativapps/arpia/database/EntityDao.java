package com.nativapps.arpia.database;

import com.nativapps.arpia.database.exception.DatabaseException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 * The <strong>EntityDao</strong> class provided methods to managing entities.
 *
 * @param <T> Entity Class
 * @param <I> Data type of primary key or id of entity
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class EntityDao<T extends Serializable, I>
        implements DataAccessObject<T, I> {

    /**
     * Entity class.
     */
    protected Class<T> entityClass;

    /**
     * the name of persistence unit of datasource for connection in the
     * database.
     */
    public static final String PERSISTENCE_UNIT = "ArpiaPU";

    /**
     * The entity manager factory created by persistence unit.
     */
    private static final EntityManagerFactory FACTORY
            = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    private static final Logger LOG = Logger
            .getLogger(EntityDao.class.getName());

    public EntityDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * get entity manager factory
     *
     * @return entity manager factory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return FACTORY;
    }

    /**
     * This method create a entity manager of a factory
     *
     * @return entity manager
     */
    protected EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaQuery criteriaQuery = entityManager
                    .getCriteriaBuilder()
                    .createQuery();
            criteriaQuery.select(criteriaQuery.from(this.entityClass));

            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    @Override
    public T find(I id) {
        if (id == null)
            return null;

        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(this.entityClass, id);
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    @Override
    public T save(T entity) {
        EntityManager entityManager = getEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            T entityCreated = entityManager.merge(entity);
            transaction.commit();
            return entityCreated;
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    @Override
    public void delete(I id) {
        if (id == null)
            return;

        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();

                T entity = this.find(id);

                if (entity != null) {
                    entityManager.remove(entityManager.contains(entity)
                            ? entity : entityManager.merge(entity));
                }

                transaction.commit();
            }
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    /**
     * Get first entity from named query with given params.
     *
     * @param namedQuery named query
     * @param parameters params for insert into the named query
     * @return entity class
     */
    protected T executeNamedQuery(String namedQuery, Parameter... parameters) {
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                TypedQuery<T> typedQuery = entityManager
                        .createNamedQuery(namedQuery, entityClass);

                for (Parameter parameter : parameters) {
                    typedQuery.setParameter(parameter.key, parameter.value);
                }

                return typedQuery.getSingleResult();
            }
        } catch (NoResultException ex) {
            LOG.info(ex.getMessage());
            return null;
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
        return null;
    }

    /**
     * Get all entities from named query with given params.
     *
     * @param namedQuery named query
     * @param parameters params for insert into the named query
     * @return entity class
     */
    protected List<T> executeNamedQueryForList(String namedQuery,
            Parameter... parameters) {
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                TypedQuery<T> typedQuery = entityManager
                        .createNamedQuery(namedQuery, entityClass);

                for (Parameter parameter : parameters) {
                    typedQuery.setParameter(parameter.key, parameter.value);
                }

                return typedQuery.getResultList();
            }
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
        return null;
    }

    /**
     * Get all entities from named query with given params.
     *
     * @param namedQuery named query
     * @param start first index of result list
     * @param size max result of list
     * @param parameters params for insert into the named query
     *
     * @return entity class
     */
    protected List<T> executeNamedQueryForList(String namedQuery, int start,
            int size, Parameter... parameters) {
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                TypedQuery<T> typedQuery = entityManager
                        .createNamedQuery(namedQuery, entityClass);

                for (Parameter parameter : parameters) {
                    typedQuery.setParameter(parameter.key, parameter.value);
                }

                return typedQuery.setFirstResult(start)
                        .setMaxResults(size)
                        .getResultList();
            }
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
        return null;
    }

    /**
     * The class <code>Parameter</code> is a argument for insert in SQL, more
     * exactly in named query.
     */
    protected class Parameter {

        protected String key;

        protected Object value;

        /**
         * Create a param with key and value.
         *
         * @param key id of value.
         * @param value object of type string.
         */
        public Parameter(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
