package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.FaultData;
import com.nativapps.arpia.model.dto.FaultRequest;
import com.nativapps.arpia.model.dto.FaultResponse;
import com.nativapps.arpia.service.FaultService;
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
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class FaultResource {

    @Context
    private final HttpServletRequest request;

    private final FaultService service = ServiceFactory.getFaultService();

    public FaultResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<FaultResponse> getAll(
            @PathParam("messengerId") Long messengerId) {
        return service.getAll(messengerId);
    }

    @GET
    @Path("{index}")
    public FaultResponse get(@PathParam("index") Integer index,
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(index, messengerId);
    }

    @POST
    public Response post(@PathParam("messengerId") Long messengerId,
            FaultRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        FaultResponse fault = service.add(messengerId, data);
        URI location = uriInfo.getAbsolutePathBuilder().build();
        return Response.created(location).entity(fault).build();
    }

    @PUT
    @Path("{index}")
    public FaultResponse update(@PathParam("messengerId") Long messengerId,
            @PathParam("index") Integer index,
            FaultRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(index, messengerId, data);
    }

    @DELETE
    @Path("{index}")
    public FaultResponse delete(@PathParam("index") Integer index,
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(index, messengerId);
    }

}
