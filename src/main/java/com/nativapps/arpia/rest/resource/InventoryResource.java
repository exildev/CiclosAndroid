package com.nativapps.arpia.rest.resource;

import com.nativapps.arpia.model.dto.InventoryRequest;
import com.nativapps.arpia.model.dto.InventoryResponse;
import com.nativapps.arpia.service.InventoryService;
import com.nativapps.arpia.service.ServiceFactory;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
@Path("inventory")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class InventoryResource {

    @Context
    private HttpServletRequest request;

    private final InventoryService service = ServiceFactory
            .getInventoryService();

    @GET
    public List<InventoryResponse> getAllInventoryItems() {
        service.configurate(Collections.list(request.getLocales()));
        return service.getAll();
    }

    @GET
    @Path("{inventoryId}")
    public InventoryResponse getInvetoryItem(@PathParam("inventoryId") Long inventoryId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.get(inventoryId);
    }

    @POST
    public Response addNewInventoryItem(InventoryRequest inventory,
            @Context UriInfo uriInfo) {
        service.configurate(Collections.list(request.getLocales()));
        InventoryResponse newInventoryItem = service.add(inventory);
        String newId = newInventoryItem.getId().toString();
        URI location = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(location)
                .entity(newInventoryItem)
                .build();
    }

    @PUT
    @Path("{inventoryId}")
    public InventoryResponse updateInventoryItem(@PathParam("inventoryId") Long inventoryId,
            InventoryRequest inventory) {
        service.configurate(Collections.list(request.getLocales()));
        return service.update(inventoryId, inventory);
    }

    @DELETE
    @Path("{inventoryId}")
    public InventoryResponse deleteInventoryItem(@PathParam("inventoryId") Long inventoryId) {
        service.configurate(Collections.list(request.getLocales()));
        return service.delete(inventoryId);
    }
}
