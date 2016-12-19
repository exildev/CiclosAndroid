package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.MessengerRequest;
import com.nativapps.arpia.model.dto.MessengerResponse;
import com.nativapps.arpia.rest.bean.MessengerBean;
import com.nativapps.arpia.service.MessengerService;
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
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Path("messengers")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessengerResource {

    @Context
    private HttpServletRequest request;

    MessengerService service = ServiceFactory.getMessengerService();

    @GET
    public List<MessengerResponse> getAll(@BeanParam MessengerBean bean) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll(bean.getStart(), bean.getSize());
    }

    @GET
    @Path("{id}")
    public MessengerResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @POST
    public Response post(MessengerRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        MessengerResponse messenger = service.add(data);
        URI location = uriInfo.getAbsolutePathBuilder().path(messenger.getId().toString()).build();
        return Response.created(location).entity(messenger).build();
    }

    @PUT
    @Path("{id}")
    public MessengerResponse put(@PathParam("id") Long id, MessengerRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @DELETE
    @Path("{id}")
    public MessengerResponse delete(@PathParam("id") Long id) {
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

    @Path("{messengerId}/references")
    public ReferenceResource references() {
        return new ReferenceResource(request);
    }

    @Path("{messengerId}/faults")
    public FaultResource faults() {
        return new FaultResource(request);
    }

    @Path("{messengerId}/vehicles")
    public VehicleResource vehicles() {
        return new VehicleResource(request);
    }

    @Path("{messengerId}/workshifts")
    public WorkshiftParamResource workshifts() {
        return new WorkshiftParamResource(request);
    }

    @Path("{messengerId}/reliabilities")
    public ReliabilityResource reliabilities() {
        return new ReliabilityResource(request);
    }

    @Path("{messengerId}/actions")
    public MessengerActionResource actions() {
        return new MessengerActionResource(request);
    }

}
