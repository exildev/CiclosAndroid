package com.nativapps.arpia.rest.mapper;

import com.nativapps.arpia.model.dto.ErrorMessageData;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        Status statusCode = Response.Status.INTERNAL_SERVER_ERROR;
        ErrorMessageData error = new ErrorMessageData(statusCode.getStatusCode());
        error.addMessage(e.getMessage());
        return Response.status(statusCode)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
