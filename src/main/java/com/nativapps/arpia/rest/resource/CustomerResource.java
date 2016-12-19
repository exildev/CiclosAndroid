package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.CustomerDataDto;
import com.nativapps.arpia.service.CustomerDataService;
import com.nativapps.arpia.service.ServiceFactory;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("customers")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CustomerResource {

    @Context
    private HttpServletRequest request;

    private final CustomerDataService service = ServiceFactory
            .getCustomerDataService();

    @GET
    public List<CustomerDataDto> getAll(@QueryParam("start") int start, 
            @QueryParam("start") Integer size) {
        service.configurate(Collections.list(request.getLocales()));
        if (size == null || size <= 0)
            size = 20;
        return service.getAll(start, size);
    }

    @GET
    @Path("{id}")
    public CustomerDataDto get(@PathParam("id") Long id) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(id);
    }

    @Path("particulars")
    public ParticularResource particulars() {
        return new ParticularResource(request);
    }

    @Path("establishments")
    public EstablishmentResource establishments() {
        return new EstablishmentResource(request);
    }
    
    @Path("{customerId}/bonus")
    public BonusResource bonus() {
        return new BonusResource(request);
    }
    
    @Path("{customerId}/creditinfo")
    public CreditInfoResource creditInfo() {
        return new CreditInfoResource(request);
    }
    
    @Path("{customerId}/banned")
    public CustomerBanResource customerBanHistory() {
        return new CustomerBanResource(request);
    }
}
