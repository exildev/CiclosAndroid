package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.MessengerActionRequest;
import com.nativapps.arpia.model.dto.MessengerActionResponse;
import com.nativapps.arpia.rest.bean.MessengerActionBean;
import com.nativapps.arpia.service.MessengerActionService;
import com.nativapps.arpia.service.ServiceFactory;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Path("actions")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessengerActionResource {

    @QueryParam("messengerId")
    private Long messengerId;

    @Context
    private HttpServletRequest request;

    private final MessengerActionService service
            = ServiceFactory.getMessengerActionService();

    public MessengerActionResource() {
    }

    public MessengerActionResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<MessengerActionResponse> getAll(@BeanParam MessengerActionBean bean,
            @PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        if (messengerId == null && this.messengerId == null)
            return service.getAll();
        return service.getAll(messengerId == null ? this.messengerId : messengerId,
                bean.getStart(), bean.getSize());
    }

    @GET
    @Path("{actionId}")
    public MessengerActionResponse get(@PathParam("actionId") Long actionId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(actionId);
    }

    @POST
    public Response post(@PathParam("messengerId") Long messengerId,
            MessengerActionRequest action, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        Long mId = messengerId == null ? this.messengerId : messengerId;
        MessengerActionResponse newAction = service.add(mId, action);
        String newId = newAction.getId().toString();
        URI location = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(location)
                .entity(newAction)
                .build();
    }

    @PUT
    @Path("{actionId}")
    public MessengerActionResponse update(@PathParam("actionId") Long actionId,
            MessengerActionRequest action) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(actionId, action);
    }

    @DELETE
    @Path("{actionId}")
    public MessengerActionResponse delete(@PathParam("actionId") Long actionId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(actionId);
    }
}
