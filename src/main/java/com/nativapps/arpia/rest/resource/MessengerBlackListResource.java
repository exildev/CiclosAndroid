package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.SimpleMessengerResponse;
import com.nativapps.arpia.service.MessengerBlackListService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessengerBlackListResource {

    @Context
    private HttpServletRequest request;

    private final MessengerBlackListService service = ServiceFactory
            .getMessengerBlackListService();

    public MessengerBlackListResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<SimpleMessengerResponse> getAll(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(id);
    }

    @GET
    @Path("{index}")
    public SimpleMessengerResponse get(@PathParam("id") Long id,
            @PathParam("index") Integer index) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id, index);
    }

    @POST
    public SimpleMessengerResponse post(@PathParam("id") Long id,
            @QueryParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.add(id, messengerId);
    }

    @DELETE
    public SimpleMessengerResponse delete(@PathParam("id") Long id,
            @PathParam("index") Integer index) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id, index);
    }
}
