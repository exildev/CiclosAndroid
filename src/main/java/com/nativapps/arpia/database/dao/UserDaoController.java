package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.User;
import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.database.exception.IncorrectCredentialsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class UserDaoController extends EntityDao<User, Long>
        implements UserDao {

    private static final UserDaoController INSTANCE = new UserDaoController();

    private UserDaoController() {
        super(User.class);

        if (find(1L) == null) {
            User admin = new User("admin@arpia.com", "@rp!a", "Administrator",
                    UserType.SUPER_USER);
            admin.setUsername("admin");
            save(admin);
        }
    }

    public static UserDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public User login(String userData, String password)
            throws IncorrectCredentialsException {
        User user = executeNamedQuery("user.login",
                new Parameter("dataUser", userData),
                new Parameter("password", password));

        if (user == null)
            throw new IncorrectCredentialsException("Username or password is "
                    + "invalid.");

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return executeNamedQuery("user.findByUsername",
                new Parameter("username", username));
    }

    @Override
    public User findByEmail(String email) {
        return executeNamedQuery("user.findByEmail",
                new Parameter("email", email));
    }

    @Override
    public List<User> findAllPaginated(int start, int size) {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaQuery criteriaQuery = entityManager
                    .getCriteriaBuilder()
                    .createQuery();

            criteriaQuery.select(criteriaQuery.from(User.class));

            TypedQuery<User> typedQuery = entityManager
                    .createQuery(criteriaQuery);

            return typedQuery.setFirstResult(start)
                    .setMaxResults(size)
                    .getResultList();
        } finally {
            if (entityManager != null)
                entityManager.close();
        }
    }

    @Override
    public List<User> findAllUserByType(UserType type, int start, int size) {
        return executeNamedQueryForList("user.findByType", start, size,
                new Parameter("type", type));
    }

}
