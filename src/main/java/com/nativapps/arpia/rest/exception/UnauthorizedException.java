package com.nativapps.arpia.rest.exception;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class UnauthorizedException extends Exception {

    /**
     * Creates a new instance of <code>UnauthorizedException</code> without
     * detail message.
     */
    public UnauthorizedException() {
    }

    /**
     * Constructs an instance of <code>UnauthorizedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnauthorizedException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>UnauthorizedException</code> with the
     * specified detail message and error cause.
     *
     * @param message the detail message.
     * @param cause the error cause.
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
