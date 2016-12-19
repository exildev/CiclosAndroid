package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.MobileNumberResponse;
import com.nativapps.arpia.rest.bean.MobileNumberBean;
import com.nativapps.arpia.service.MobileNumberService;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Path("Mobiles")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MobileNumberResource {

    @Context
    private HttpServletRequest request;

    private final MobileNumberService service
            = ServiceFactory.getMobileNumberService();

    @POST
    public Response add(@BeanParam MobileNumberBean bean,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        List<MobileNumberResponse> responses
                = service.add(bean.getQuantity(), bean.getOperationId());
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(bean.getOperationId().toString()).build();
        return Response.created(location).entity(responses).build();
    }

}
