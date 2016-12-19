/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nativapps.arpia.database.exception;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class DatabaseException extends RuntimeException {

    /**
     * Creates a new instance of <code>DatabaseException</code> without detail
     * message.
     */
    public DatabaseException() {
    }

    /**
     * Constructs an instance of <code>DatabaseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatabaseException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>DatabaseException</code> with the
     * specified cause and with detail message.
     *
     * @param message the detail message
     * @param cause throwable cause
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
