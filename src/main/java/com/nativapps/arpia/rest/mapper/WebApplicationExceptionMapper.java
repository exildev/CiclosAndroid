package com.nativapps.arpia.rest.mapper;

import com.nativapps.arpia.model.dto.ErrorMessageData;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Provider
public class WebApplicationExceptionMapper
        implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException e) {
        ErrorMessageData error = new ErrorMessageData(e.getResponse()
                .getStatus());
        error.addMessage(e.getMessage());
        return Response.fromResponse(e.getResponse())
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
