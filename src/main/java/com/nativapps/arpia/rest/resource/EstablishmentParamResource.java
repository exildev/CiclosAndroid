package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.EstablishmentParamRequest;
import com.nativapps.arpia.model.dto.EstablishmentParamResponse;
import com.nativapps.arpia.service.EstablishmentParamService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EstablishmentParamResource {

    @Context
    private HttpServletRequest request;

    private final EstablishmentParamService service = ServiceFactory
            .getEstablishmentParamService();

    public EstablishmentParamResource(HttpServletRequest request) {
        this.request = request;
    }

    @GET
    public EstablishmentParamResponse get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @PUT
    public EstablishmentParamResponse put(@PathParam("id") Long id,
            EstablishmentParamRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(id, data);
    }

    @Path("blacklist")
    public MessengerBlackListResource blacklist() {
        return new MessengerBlackListResource(request);
    }
}
