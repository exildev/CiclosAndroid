package com.nativapps.arpia.database.exception;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class IncorrectCredentialsException extends Exception {

    /**
     * Creates a new instance of <code>UnauthorizedException</code> without
     * detail message.
     */
    public IncorrectCredentialsException() {
    }

    /**
     * Constructs an instance of <code>UnauthorizedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectCredentialsException(String msg) {
        super(msg);
    }

    /**
     * Contructs an instance of <code>UnauthorizedException</code> with the
     * specified detail message and cause.
     *
     * @param message the detail message
     * @param cause error causes
     */
    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}
