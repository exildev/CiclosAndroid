package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.LoginData;
import com.nativapps.arpia.model.dto.TokenData;
import com.nativapps.arpia.rest.TokenUtil;
import com.nativapps.arpia.rest.UserInfo;
import com.nativapps.arpia.rest.exception.TokenException;
import com.nativapps.arpia.rest.exception.UnauthorizedException;
import com.nativapps.arpia.service.AccountService;
import com.nativapps.arpia.service.ServiceFactory;
import com.nativapps.arpia.util.TextUtil;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOG = Logger
            .getLogger(AccountResource.class.getName());

    @Context
    private HttpServletRequest request;

    private final AccountService service = ServiceFactory.getUserService();

    @POST
    @Path("authenticate")
    public TokenData login(LoginData login) {
        service.configurate(Collections.list(request.getLocales()));
        if (login == null)
            throw new BadRequestException("The login information is required.");
        return service.login(login.getUsername(), login.getPassword());
    }

    @POST
    @Path("refresh")
    public TokenData refresh(@HeaderParam("Authorization") String authHeader) {
        try {
            if (TextUtil.isEmpty(authHeader))
                throw new BadRequestException("The Authorization header param "
                        + "is required.");

            String authToken = authHeader.replaceAll("Bearer ", "");
            UserInfo userInfo = TokenUtil.validateToken(authToken);

            return userInfo != null ? new TokenData(TokenUtil.generateToken(userInfo)) : null;
        } catch (TokenException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex.getMessage(), ex);
        } catch (UnauthorizedException ex) {
            throw new WebApplicationException(ex.getMessage(),
                    Response.Status.UNAUTHORIZED);
        }
    }

}
