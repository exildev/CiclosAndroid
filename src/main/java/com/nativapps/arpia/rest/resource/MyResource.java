package com.nativapps.arpia.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Gustavo Pacheco <gpacheco@nativapps.mobi>
 * @version 1.0
 */
@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "It's work!";
    }

}
