package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.AddressRequest;
import com.nativapps.arpia.model.dto.AddressResponse;
import com.nativapps.arpia.service.AddressService;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
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
public class AddressResource {

    @Context
    private HttpServletRequest request;

    private final AddressService service = ServiceFactory.getAddressService();

    public AddressResource() {
    }

    public AddressResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<AddressResponse> getAll(@PathParam("ownerId") Long ownerid,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        String url = uriInfo.getAbsolutePath().toString();
        if (url.contains("particular") || url.contains("messengers")
                || url.contains("administrators"))
            return service.getAll(ownerid, AddressService.Owner.PERSON);
        else if (url.contains("establishments"))
            return service.getAll(ownerid, AddressService.Owner.ESTABLISHMENT);

        throw new NotAllowedException("The URI is wrong");
    }

    @GET
    @Path("{id}")
    public AddressResponse get(@PathParam("id") Long id,
            @PathParam("ownerId") Long ownerId, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        String url = uriInfo.getAbsolutePath().toString();

        if (url.contains("particular") || url.contains("messengers")
                || url.contains("administrators"))
            return service.get(id, ownerId, AddressService.Owner.PERSON);
        else if (url.contains("establishments"))
            return service.get(id, ownerId, AddressService.Owner.ESTABLISHMENT);

        throw new NotAllowedException("The URI is wrong");
    }

    @POST
    public Response post(@PathParam("ownerId") Long ownerId,
            AddressRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        AddressResponse newAddress = null;
        String url = uriInfo.getAbsolutePath().toString();

        if (url.contains("particular") || url.contains("messengers")
                || url.contains("administrators"))
            newAddress = service.add(ownerId,
                    AddressService.Owner.PERSON, data);
        else if (url.contains("establishments"))
            newAddress = service.add(ownerId, AddressService.Owner.ESTABLISHMENT,
                    data);

        if (newAddress != null) {
            URI location = uriInfo.getAbsolutePathBuilder().path(newAddress
                    .getId().toString()).build();
            return Response.created(location).entity(newAddress).build();
        }

        throw new NotAllowedException("The URI is wrong");
    }

    @PUT
    @Path("{id}")
    public AddressResponse put(@PathParam("id") Long id,
            @PathParam("ownerId") Long ownerId, AddressRequest data,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        String url = uriInfo.getAbsolutePath().toString();

        if (url.contains("particular") || url.contains("messengers")
                || url.contains("administrators"))
            return service.update(id, ownerId,
                    AddressService.Owner.PERSON, data);
        else if (url.contains("establishments"))
            return service.update(id, ownerId, AddressService.Owner.ESTABLISHMENT,
                    data);

        throw new NotAllowedException("The URI is wrong");
    }

    @DELETE
    @Path("{id}")
    public AddressResponse delete(@PathParam("id") Long id,
            @PathParam("ownerId") Long ownerId, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        String url = uriInfo.getAbsolutePath().toString();
        if (url.contains("particular") || url.contains("messengers")
                || url.contains("administrators"))
            return service.delete(id, ownerId,
                    AddressService.Owner.PERSON);
        else if (url.contains("establishments"))
            return service.delete(id, ownerId, AddressService.Owner.ESTABLISHMENT);

        throw new NotAllowedException("The URI is wrong");
    }
}
