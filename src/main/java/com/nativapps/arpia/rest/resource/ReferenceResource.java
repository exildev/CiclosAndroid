package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.ReferenceData;
import com.nativapps.arpia.model.dto.ReferenceRequest;
import com.nativapps.arpia.model.dto.ReferenceResponse;
import com.nativapps.arpia.service.ReferenceService;
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
public class ReferenceResource {

    @Context
    private final HttpServletRequest request;

    private final ReferenceService service = ServiceFactory
            .getReferenceService();

    public ReferenceResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<ReferenceResponse> getAll(
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(messengerId);
    }

    @GET
    @Path("{index}")
    public ReferenceResponse get(@PathParam("index") Integer index,
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(index, messengerId);
    }

    @POST
    public Response post(@PathParam("messengerId") Long messengerId,
            ReferenceRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        ReferenceData reference = service.add(messengerId, data);
        URI location = uriInfo.getAbsolutePathBuilder().build();
        return Response.created(location).entity(reference).build();
    }

    @PUT
    @Path("{index}")
    public ReferenceResponse update(@PathParam("messengerId") Long messengerId,
            @PathParam("index") Integer index,
            ReferenceRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(index, messengerId, data);
    }

    @DELETE
    @Path("{index}")
    public ReferenceResponse delete(@PathParam("index") Integer index,
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(index, messengerId);
    }

}
