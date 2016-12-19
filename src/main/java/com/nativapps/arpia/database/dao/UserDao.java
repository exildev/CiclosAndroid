package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.User;
import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.database.exception.IncorrectCredentialsException;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface UserDao extends DataAccessObject<User, Long> {

    /**
     * Get user id from login.
     *
     * @param userData username or email
     * @param password password
     * @return long, user id
     * @throws IncorrectCredentialsException if the credentials are incorrect
     */
    User login(String userData, String password)
            throws IncorrectCredentialsException;

    /**
     * Get a user from your username.
     *
     * @param username username
     * @return User information
     */
    User findByUsername(String username);

    /**
     * Get a user from your main email.
     *
     * @param email email address
     * @return User information
     */
    User findByEmail(String email);

    /**
     * Get list of users paginated.
     *
     * @param start initial index
     * @param size size of list
     * @return user entity list
     */
    List<User> findAllPaginated(int start, int size);

    /**
     * Get a users list paginated filtred by type of user.
     *
     * @param type user type enumeration
     * @param start intial index
     * @param size size of list
     * @return user entity list
     */
    List<User> findAllUserByType(UserType type, int start, int size);

}
