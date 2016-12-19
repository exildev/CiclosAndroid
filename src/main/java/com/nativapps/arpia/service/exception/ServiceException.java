package com.nativapps.arpia.service.exception;

import com.nativapps.arpia.model.dto.ErrorMessageData;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class ServiceException extends RuntimeException {

    private ErrorMessageData errorMessage;

    /**
     * Creates a new instance of <code>ServiceException</code> without detail
     * message.
     */
    public ServiceException() {
    }

    /**
     * Constructs an instance of <code>ServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServiceException(String msg) {
        super(msg);
    }

    /**
     *
     * @param errorMessage
     */
    public ServiceException(ErrorMessageData errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessageData getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessageData errorMessage) {
        this.errorMessage = errorMessage;
    }

}
