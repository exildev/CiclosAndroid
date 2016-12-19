package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.NeighborhoodRequest;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import com.nativapps.arpia.service.NeighborhoodService;
import com.nativapps.arpia.service.ServiceFactory;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Path("neighborhoods")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class NeighborhoodResource {

    @Context
    private HttpServletRequest request;

    private final NeighborhoodService service = ServiceFactory
            .getNeighborhoodService();

    @GET
    public List<NeighborhoodResponse> getAll(@QueryParam("start") int start,
            @QueryParam("size") Integer size) {
        if (size == null)
            size = 20;
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(start, size);
    }

    @GET
    @Path("{id}")
    public NeighborhoodResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @POST
    public Response post(NeighborhoodRequest data, 
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        NeighborhoodResponse response = service.add(data);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(response).build();
    }

    @PUT
    @Path("{id}")
    public NeighborhoodResponse put(@PathParam("id") Long id,
            NeighborhoodRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public NeighborhoodResponse delete(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
    }

    @GET
    @Path("name/{name}")
    public NeighborhoodResponse getByName(@PathParam("name") String name) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getByName(name);
    }
}
