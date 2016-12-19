package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.EstablishmentRequest;
import com.nativapps.arpia.model.dto.EstablishmentResponse;
import com.nativapps.arpia.rest.bean.EstablishmentBean;
import com.nativapps.arpia.service.EstablishmentService;
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
public class EstablishmentResource {

    @Context
    private HttpServletRequest request;
    
    private final EstablishmentService service = ServiceFactory
            .getEstablishmentService();

    public EstablishmentResource(HttpServletRequest request) {
        this.request = request;
    }
    
    @GET
    public List<EstablishmentResponse> getAll(@BeanParam EstablishmentBean bean) {
        if (bean.getSize() == null)
            bean.setSize(20);
        return service.getAll(bean.getStart(), bean.getSize(), bean.getSearch(), 
                bean.getOrderBy(), bean.getOrderType());
    }

    @GET
    @Path("{id}")
    public EstablishmentResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @POST
    public Response post(EstablishmentRequest data,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        EstablishmentResponse newEstablishment = service.add(data);
        URI location = uriInfo.getAbsolutePathBuilder().path(newEstablishment
                .getId().toString()).build();
        return Response.created(location).entity(newEstablishment).build();
    }

    @PUT
    @Path("{id}")
    public EstablishmentResponse put(@PathParam("id") Long id,
            EstablishmentRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public EstablishmentResponse delete(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
    }

    @Path("{ownerId}/addresses")
    public AddressResource addresses() {
        return new AddressResource(request);
    }

    @Path("{establishmentId}/administrators")
    public AdministratorResource administrators() {
        return new AdministratorResource(request);
    }

    @Path("{id}/params")
    public EstablishmentParamResource params() {
        return new EstablishmentParamResource(request);
    }
}
