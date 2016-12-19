package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.ReliabilityRequest;
import com.nativapps.arpia.model.dto.ReliabilityResponse;
import com.nativapps.arpia.service.ReliabilityService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ReliabilityResource {

    @Context
    private HttpServletRequest request;

    private final ReliabilityService service = ServiceFactory
            .getReliabilityService();

    public ReliabilityResource() {
    }

    public ReliabilityResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public ReliabilityResponse get(@PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(messengerId);
    }

    @PUT
    public ReliabilityResponse update(@PathParam("messengerId") Long messengerId,
            ReliabilityRequest reliability) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(messengerId, reliability);
    }

}
