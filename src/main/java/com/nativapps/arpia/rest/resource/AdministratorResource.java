package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.AdministratorRequest;
import com.nativapps.arpia.model.dto.AdministratorResponse;
import com.nativapps.arpia.service.AdministratorService;
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
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class AdministratorResource {
    
    @Context
    private HttpServletRequest request;

    private final AdministratorService service = ServiceFactory
            .getAdministratorService();

    public AdministratorResource() {
    }

    public AdministratorResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<AdministratorResponse> getAll(
            @PathParam("establishmentId") Long establishmentId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(establishmentId);
    }

    @GET
    @Path("{id}")
    public AdministratorResponse get(
            @PathParam("establishmentId") Long establishmentId,
            @PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(establishmentId, id);
    }

    @POST
    public Response post(@PathParam("establishmentId") Long establishmentId,
            AdministratorRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        AdministratorResponse administrator = service.add(establishmentId, 
                data);
        URI location = uriInfo.getAbsolutePathBuilder().path(administrator
                .getId().toString()).build();
        return Response.created(location)
                .entity(administrator)
                .build();
    }

    @PUT
    @Path("{id}")
    public AdministratorResponse put(
            @PathParam("establishmentId") Long establishmentId,
            @PathParam("id") Long id, AdministratorRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(establishmentId, id, data);
    }

    @DELETE
    @Path("{id}")
    public AdministratorResponse delete(
            @PathParam("establishmentId") Long establishmentId,
            @PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(establishmentId, id);
    }
    
    @Path("{ownerId}/addresses")
    public AddressResource addresses() {
        return new AddressResource(request);
    }
    
    @Path("{ownerId}/emails")
    public EmailResource emails() {
        return new EmailResource(request);
    }
    
    @Path("{ownerId}/phones")
    public PhoneResource phones() {
        return new PhoneResource(request);
    }
}
