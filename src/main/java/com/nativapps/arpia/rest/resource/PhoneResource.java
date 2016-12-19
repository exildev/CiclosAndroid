package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.PhoneRequest;
import com.nativapps.arpia.model.dto.PhoneResponse;
import com.nativapps.arpia.service.PhoneService;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class PhoneResource {

    @Context
    private HttpServletRequest request;
    
    private final PhoneService service = ServiceFactory.getPhoneService();

    public PhoneResource() {
    }

    public PhoneResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<PhoneResponse> getAll(@PathParam("ownerId") Long ownerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(ownerId);
    }

    @GET
    @Path("{index}")
    public PhoneResponse get(@PathParam("ownerId") Long ownerId,
            @PathParam("index") Integer index) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(index, ownerId);
    }

    @POST
    public Response post(@PathParam("ownerId") Long ownerId, 
            PhoneRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        PhoneResponse newPhone = service.add(ownerId, data);
        URI location = uriInfo.getAbsolutePath();
        return Response.created(location).entity(newPhone).build();
    }

    @DELETE
    @Path("{index}")
    public PhoneResponse delete(@PathParam("ownerId") Long ownerId,
            @PathParam("index") Integer index) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(index, ownerId);
    }
}
