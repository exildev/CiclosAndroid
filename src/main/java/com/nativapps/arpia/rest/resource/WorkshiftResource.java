package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.WorkshiftRequest;
import com.nativapps.arpia.model.dto.WorkshiftResponse;
import com.nativapps.arpia.service.ServiceFactory;
import com.nativapps.arpia.service.WorkshiftService;
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
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Path("workshifts")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class WorkshiftResource {

    @Context
    private HttpServletRequest request;

    private final WorkshiftService service = ServiceFactory.getWorkshiftService();

    @POST
    public Response post(WorkshiftRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        WorkshiftResponse workshift = service.add(data);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(workshift.getId().toString())
                .build();
        return Response.created(location)
                .entity(workshift)
                .build();
    }

    @GET
    @Path("{id}")
    public WorkshiftResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @GET
    public List<WorkshiftResponse> getAll() {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll();
    }

    @PUT
    @Path("{id}")
    public WorkshiftResponse put(@PathParam("id") Long id, WorkshiftRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public WorkshiftResponse delete(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
    }

}
