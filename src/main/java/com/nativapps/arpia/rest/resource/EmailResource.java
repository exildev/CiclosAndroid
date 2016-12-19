package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.EmailData;
import com.nativapps.arpia.model.dto.EmailRequest;
import com.nativapps.arpia.model.dto.EmailResponse;
import com.nativapps.arpia.service.EmailService;
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
public class EmailResource {

    @Context
    private HttpServletRequest request;
    
    private final EmailService service = ServiceFactory.getEmailService();

    public EmailResource() {
    }

    public EmailResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public List<EmailResponse> getAll(@PathParam("ownerId") Long ownerid) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAllByPersonId(ownerid);
    }

    @GET
    @Path("{index}")
    public EmailResponse get(@PathParam("ownerId") Long ownerid,
            @PathParam("index") Integer index) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(index, ownerid);
    }

    @POST
    public Response post(@PathParam("ownerId") Long ownerid, 
            EmailRequest data, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        EmailResponse newEmail = service.add(ownerid, data);
        return Response.created(uriInfo.getAbsolutePath())
                .entity(newEmail).build();
    }

    @DELETE
    @Path("{index}")
    public EmailResponse delete(@PathParam("ownerId") Long ownerid,
            @PathParam("index") Integer index, EmailData data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(index, ownerid);
    }

}
