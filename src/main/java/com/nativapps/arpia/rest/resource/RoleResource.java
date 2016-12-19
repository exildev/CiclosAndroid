package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.RoleRequest;
import com.nativapps.arpia.model.dto.RoleResponse;
import com.nativapps.arpia.service.RoleService;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@Path("roles")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class RoleResource {

    @Context
    private HttpServletRequest request;

    private final RoleService service = ServiceFactory.getRoleService();

    @GET
    public List<RoleResponse> getAllRoles(@Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAllRoles();
    }

    @GET
    @Path("{roleId}")
    public RoleResponse getRoleById(@PathParam("roleId") Long id,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getRole(id);
    }

    @POST
    public Response addNewRole(RoleRequest role, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        RoleResponse newRole = service.add(role);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(newRole.getId().toString())
                .build();
        return Response.created(uri)
                .entity(newRole)
                .build();
    }

    @PUT
    @Path("{roleId}")
    public RoleResponse updateRole(@PathParam("roleId") Long id,
            RoleRequest role) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, role);
    }

    @DELETE
    @Path("{roleId}")
    public RoleResponse deleteRole(@PathParam("roleId") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
    }

}
