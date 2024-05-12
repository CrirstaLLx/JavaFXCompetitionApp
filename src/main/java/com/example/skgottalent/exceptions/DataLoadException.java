/**
 * The DataLoadException class represents an exception that occurs when there is a failure to load data in the Slovakia Got Talent application.
 * It extends the Exception class.
 */
package com.example.skgottalent.exceptions;

public class DataLoadException extends Exception {

    /**
     * Constructs a new DataLoadException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DataLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
