package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.ParticularRequest;
import com.nativapps.arpia.model.dto.ParticularResponse;
import com.nativapps.arpia.rest.bean.ParticularBean;
import com.nativapps.arpia.service.ParticularService;
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
public class ParticularResource {

    @Context
    private HttpServletRequest request;
    
    private final ParticularService service = ServiceFactory
            .getParticularService();

    public ParticularResource(HttpServletRequest request) {
        this.request = request;
    }
    
    @GET
    public List<ParticularResponse> getAll(@BeanParam ParticularBean bean) {
        if (bean.getSize() == null)
            bean.setSize(20);
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(bean.getStart(), bean.getSize(), bean.getSearch(), 
                bean.getOrderBy(), bean.getOrderType(), bean.getGender());
    }

    @GET
    @Path("{id}")
    public ParticularResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @POST
    public Response post(ParticularRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        ParticularResponse newParticular = service.add(data);
        URI location = uriInfo.getAbsolutePathBuilder().path(newParticular
                .getId().toString()).build();
        return Response.created(location).entity(newParticular).build();
    }

    @PUT
    @Path("{id}")
    public ParticularResponse put(@PathParam("id") Long id, 
            ParticularRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public ParticularResponse delete(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(id);
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

    @Path("{id}/params")
    public ParticularParamResource params() {
        return new ParticularParamResource(request);
    }
}
