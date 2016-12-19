package com.nativapps.arpia.rest.filter;

import com.nativapps.arpia.database.EntityControllerFactory;
import com.nativapps.arpia.database.dao.RoleDao;
import com.nativapps.arpia.database.entity.Permission;
import com.nativapps.arpia.database.entity.Role;
import com.nativapps.arpia.model.dto.ErrorMessageData;
import com.nativapps.arpia.rest.TokenUtil;
import com.nativapps.arpia.rest.UserInfo;
import com.nativapps.arpia.rest.exception.TokenException;
import com.nativapps.arpia.rest.exception.UnauthorizedException;
import com.nativapps.arpia.util.TextUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger
            .getLogger(AuthorizationFilter.class.getName());

    /**
     * Name of authorization header param.
     */
    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Prefix of the value of authorization header param.
     */
    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        PathSegment segment = request.getUriInfo()
                .getPathSegments()
                .iterator()
                .next();

        if ("accounts".equals(segment.getPath()))
            return;

        UserInfo userInfo = null;

        String authHeader = request.getHeaderString(AUTHORIZATION_HEADER);
        if (TextUtil.isEmpty(authHeader)) {
            String msg = "missing authorization header.";
            abort(request, Response.Status.UNAUTHORIZED, msg);
            return;
        } else if (!authHeader.contains(AUTHORIZATION_PREFIX)) {
            String msg = "The prefix missing from the authorization token.";
            abort(request, Response.Status.UNAUTHORIZED, msg);
            return;
        } else {
            String authToken = authHeader.replace(AUTHORIZATION_PREFIX, "");
            if (!TextUtil.isEmpty(authToken)) {
                try {
                    userInfo = TokenUtil.validateToken(authToken);
                } catch (TokenException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                    String msg = String.format("Error code %02d: %s",
                            ex.getCode(), ex.getMessage());
                    abort(request, Response.Status.INTERNAL_SERVER_ERROR, msg);
                    return;
                } catch (UnauthorizedException ex) {
                    abort(request, Response.Status.UNAUTHORIZED, ex.getMessage());
                    return;
                }
            }
        }

        if (userInfo == null) {
            String msg = "The user information is null, he could not get data "
                    + "from the payload part.";
            abort(request, Response.Status.INTERNAL_SERVER_ERROR, msg);
            return;
        }

        switch (userInfo.getType()) {
            case SUPER_USER:
                return;
            case OPERATOR:
                validateAccessResource(userInfo.getRoleId(), segment, request);
            default:
                String msg = "So far it not defined the access of this user.";
                abort(request, Response.Status.FORBIDDEN, msg);
        }
    }

    /**
     * Abort request with http response.
     *
     * @param request request context.
     * @param status http status code.
     * @param message the detail message.
     */
    private void abort(ContainerRequestContext request, Response.Status status,
            String message) {
        request.abortWith(createResponse(status, message));
    }

    /**
     * Create a response from status code and detail message.
     *
     * @param status status code.
     * @param message the detail message.
     *
     * @return http response.
     */
    private Response createResponse(Response.Status status, String message) {
        ErrorMessageData emd = new ErrorMessageData(status.getStatusCode());
        emd.addMessage(message);
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(emd)
                .build();
    }

    /**
     * Valid that users have access to resources depending on their role.
     *
     * @param roleId role ID.
     * @param segment URL segment.
     * @param request request context.
     */
    private void validateAccessResource(Long roleId, PathSegment segment,
            ContainerRequestContext request) {
        RoleDao controller = EntityControllerFactory.getRoleController();
        Role role = controller.find(roleId);

        Permission permission = null;
        for (Permission temporalPermission : role.getPermissions()) {
            if (temporalPermission.getFunctionality().toString()
                    .equalsIgnoreCase(segment.getPath())) {
                permission = temporalPermission;
                break;
            }
        }
        if (permission != null) {
            switch (permission.getPermissionType()) {
                case READ:
                    if ("GET".equals(request.getMethod()))
                        return;
                    break;
                case WRITE:
                    if (request.getMethod().matches("GET|POST|PUT"))
                        return;
                    break;
                case ADMIN:
                    return;
            }
        }
        String msg = "User cannot access the resource.";
        abort(request, Response.Status.FORBIDDEN, msg);
    }

}
