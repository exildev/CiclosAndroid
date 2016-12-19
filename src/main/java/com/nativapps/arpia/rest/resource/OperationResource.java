package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.OperationRequest;
import com.nativapps.arpia.model.dto.OperationResponse;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
import java.util.List;
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
import com.nativapps.arpia.service.OperationService;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Path("operations")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class OperationResource {

    @Context
    private HttpServletRequest request;

    private final OperationService service = ServiceFactory.getOperationService();

    @GET
    public List<OperationResponse> getList() {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll();
    }

    @GET
    @Path("{operationId}")
    public OperationResponse getCompany(@PathParam("operationId") Long operationId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(operationId);
    }

    @POST
    public Response addCompany(OperationRequest company, @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        OperationResponse newCompany = service.add(company);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(newCompany.getId().toString())
                .build();
        return Response.created(location)
                .entity(newCompany)
                .build();
    }

    @PUT
    @Path("{operationId}")
    public OperationResponse updateCompany(@PathParam("operationId") Long operationId,
            OperationRequest company) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(operationId, company);
    }

    @DELETE
    @Path("{operationId}")
    public OperationResponse deleteCompany(@PathParam("operationId") Long operationId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(operationId);
    }

}
