package com.nativapps.arpia.service;

import com.nativapps.arpia.model.dto.TokenData;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface AccountService extends Service {

    /**
     * Get authorization token if the user has successfully logged.
     *
     * @param userData user information. Ex. username, email.
     * @param password password.
     *
     * @return authorization token.
     */
    TokenData login(String userData, String password);

}
