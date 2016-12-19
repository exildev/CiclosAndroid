package com.nativapps.arpia.rest.exception;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class TokenException extends Exception {

    /**
     * Error code.
     */
    private final int code;

    /**
     * Creates a new instance of <code>TokenException</code> with the specified
     * error code.
     *
     * @param code error code.
     */
    public TokenException(int code) {
        this.code = code;
    }

    /**
     * Constructs an instance of <code>TokenException</code> with the specified
     * detail message and error code.
     *
     * @param msg the detail message.
     * @param code error code.
     */
    public TokenException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    /**
     * Cosntructs an instance of <code>TokenException</code> with the specified
     * detail message, error code and cause.
     *
     * @param msg the detail message.
     * @param code error code.
     * @param cause the cause of the error
     */
    public TokenException(String msg, int code, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
