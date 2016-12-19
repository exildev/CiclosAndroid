package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.UserRequest;
import com.nativapps.arpia.model.dto.UserResponse;
import com.nativapps.arpia.rest.bean.UserFilterBean;
import com.nativapps.arpia.service.ServiceFactory;
import com.nativapps.arpia.service.UserService;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class UserResource {

    @Context
    private HttpServletRequest request;

    private final UserService service = ServiceFactory.getUserService();

    @GET
    public List<UserResponse> getAllUsers(@BeanParam UserFilterBean bean) {
        service.configurate(Collections.list(request.getLocales()));
        if (bean.getSize() == null)
            bean.setSize(20);

        if (bean.getType() != null)
            return service.getAllUserByTypePaginated(bean.getType(),
                    bean.getStart(), bean.getSize());

        return service.getAllUsersPaginated(bean.getStart(), bean.getSize());
    }

    @GET
    @Path("{userId}")
    public UserResponse getUser(@PathParam("userId") Long userId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getUser(userId);
    }

    @POST
    public Response addNewUser(UserRequest userData, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        UserResponse newUser = service.addNewUser(userData);
        String newId = newUser.getId().toString();
        URI location = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(location)
                .entity(newUser)
                .build();
    }

    @PUT
    @Path("{userId}")
    public UserResponse updateUser(@PathParam("userId") Long userId,
            UserRequest user) {
        service.configurate(Collections.list(request.getLocales()));
        return service.updateUser(userId, user);
    }

    @DELETE
    @Path("{userId}")
    public UserResponse deleteUser(@PathParam("userId") Long userId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.deleteUser(userId);
    }

}
