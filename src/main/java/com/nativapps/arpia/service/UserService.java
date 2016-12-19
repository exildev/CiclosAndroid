package com.nativapps.arpia.service;

import com.nativapps.arpia.database.entity.UserType;
import com.nativapps.arpia.model.dto.UserRequest;
import com.nativapps.arpia.model.dto.UserResponse;
import java.util.List;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface UserService extends Service, AccountService {

    /**
     * Get a list of users information.
     *
     * @param start first user to get
     * @param size number of users to get
     * @return users list
     */
    List<UserResponse> getAllUsersPaginated(int start, int size);

    /**
     * Get a list of users information, based on its type.
     *
     * @param type type of user
     * @param start first user to get
     * @param size number of users to get
     * @return users list
     */
    List<UserResponse> getAllUserByTypePaginated(UserType type, int start, int size);

    /**
     * Get a user information.
     *
     * @param userId user ID
     * @return user information
     */
    UserResponse getUser(Long userId);

    /**
     * Create a new user in the platform.
     *
     * @param user user data
     * @return user information
     */
    UserResponse addNewUser(UserRequest user);

    /**
     * Update user information from your ID.
     *
     * @param userId user ID
     * @param user user data
     * @return user information
     */
    UserResponse updateUser(Long userId, UserRequest user);

    /**
     * Delete a user from your ID.
     *
     * @param userId user ID
     * @return user information
     */
    UserResponse deleteUser(Long userId);

}
