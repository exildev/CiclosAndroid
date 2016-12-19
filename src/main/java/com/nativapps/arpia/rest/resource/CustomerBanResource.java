package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.CustomerBanHistoryRequest;
import com.nativapps.arpia.model.dto.CustomerBanHistoryResponse;
import com.nativapps.arpia.service.CustomerBanService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CustomerBanResource {

    @Context
    private HttpServletRequest request;
    
    private final CustomerBanService service = ServiceFactory
            .getCustomerBanService();

    public CustomerBanResource(HttpServletRequest request) {
        this.request = request;
    }
    
    @GET
    @Path("history")
    public List<CustomerBanHistoryResponse> getAll(
            @PathParam("customerId") Long customerId, 
            @QueryParam("start") int start, @QueryParam("size") Integer size) {
        service.configurate(Collections.list(request.getLocales()));
        if (size == null || size < 0)
            size = 20;
        return service.getAll(start, size, customerId);
    }
    
    @GET
    @Path("last")
    public CustomerBanHistoryResponse getLastBan(
            @PathParam("customerId") Long customerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.getLastBan(customerId);
    }
    
    @POST
    public CustomerBanHistoryResponse ban(
            @PathParam("customerId") Long customerId, 
            CustomerBanHistoryRequest data){
        service.configurate(Collections.list(request.getLocales()));
        return service.ban(customerId, data);
    }
    
    @PUT
    @Path("cancel")
    public CustomerBanHistoryResponse cancelBan(
            @PathParam("customerId") Long customerId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.cancelBan(customerId);
    }
}
