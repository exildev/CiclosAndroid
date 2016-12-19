package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.WorkshiftParamRequest;
import com.nativapps.arpia.model.dto.WorkshiftParamResponse;
import com.nativapps.arpia.service.ServiceFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.nativapps.arpia.service.WorkshiftParamService;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class WorkshiftParamResource {

    @Context
    private HttpServletRequest request;

    private final WorkshiftParamService service = ServiceFactory
            .getWorkShiftParamService();

    public WorkshiftParamResource() {
    }

    public WorkshiftParamResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public WorkshiftParamResponse get(@PathParam("messengerId") Long messengerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(messengerId);
    }

    @PUT
    public WorkshiftParamResponse update(@PathParam("messengerId") Long messengerId,
            WorkshiftParamRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(messengerId, data);
    }

}
