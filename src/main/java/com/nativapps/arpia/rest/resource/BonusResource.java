package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.BonusRequest;
import com.nativapps.arpia.model.dto.BonusResponse;
import com.nativapps.arpia.service.BonusService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class BonusResource {

    @Context
    private final HttpServletRequest request;
    
    private final BonusService service = ServiceFactory.getBonusService();

    public BonusResource(HttpServletRequest request) {
        this.request = request;
    }
    
    @GET
    public List<BonusResponse> getAll(@QueryParam("start") int start,
            @QueryParam("size") Integer size,
            @PathParam("customerId") Long customerId) {
        service.configurate(Collections.list(request.getLocales()));
        if (size == null || size <= 0)
            size = 20;
        return service.getAll(start, size, customerId);
    }

    @GET
    @Path("bonusId")
    public BonusResponse get(@PathParam("customerId") Long customerId,
            @PathParam("bonusId") Long bonusId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(customerId, bonusId);
    }

    @POST
    public BonusResponse post(@PathParam("customerId") Long customerId,
            @HeaderParam("Authorization") String authToken, BonusRequest bonus) {
        service.configurate(Collections.list(request.getLocales()));
        return service.add(customerId, authToken, bonus);
    }

    @PUT
    @Path("bonusId")
    public BonusResponse put(@PathParam("customerId") Long customerId,
            @PathParam("bonusId") Long bonusId,
            @HeaderParam("Authorization") String authToken, BonusRequest bonus) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(customerId, bonusId, authToken, bonus);
    }
    
    @DELETE
    @Path("bonusId")
    public BonusResponse delete(@PathParam("customerId") Long customerId,
            @PathParam("bonusId") Long bonusId){
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(customerId, bonusId);
    }
}
