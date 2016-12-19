package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.CreditInfoDataRequest;
import com.nativapps.arpia.model.dto.CreditInfoDataResponse;
import com.nativapps.arpia.service.CreditInfoService;
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
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CreditInfoResource {

    @Context
    private HttpServletRequest request;
    
    private final CreditInfoService service = ServiceFactory
            .getCreditInfoService();

    public CreditInfoResource(HttpServletRequest request) {
        this.request = request;
    }
    
    @GET
    public CreditInfoDataResponse get(@PathParam("customerId") Long customerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(customerId);
    }
    
    @PUT
    public CreditInfoDataResponse put(@PathParam("customerId") Long customerId, 
            CreditInfoDataRequest data) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(customerId, data);
    }
}
