package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.VehicleRequest;
import com.nativapps.arpia.model.dto.VehicleResponse;
import com.nativapps.arpia.service.ServiceFactory;
import com.nativapps.arpia.service.VehicleService;
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
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Path("vehicles")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class VehicleResource {

    @Context
    private final HttpServletRequest request;

    private final VehicleService service = ServiceFactory.getVehicleService();

    public VehicleResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<VehicleResponse> getAll(
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(messengerId);
    }

    @GET
    @Path("{id}")
    public VehicleResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @POST
    public Response post(@PathParam("messengerId") Long messengerId,
            VehicleRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        VehicleResponse vehicle = service.add(messengerId, data);
        URI location = uriInfo.getAbsolutePathBuilder().build();
        return Response.created(location).entity(vehicle).build();
    }

    @PUT
    @Path("{id}")
    public VehicleResponse update(@PathParam("id") Long id,
            VehicleRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public VehicleResponse delete(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
    }
}
