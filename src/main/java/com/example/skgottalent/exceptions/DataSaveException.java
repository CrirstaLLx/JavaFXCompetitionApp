/**
 * The DataSaveException class represents an exception that occurs when there is a failure to save data in the Slovakia Got Talent application.
 * It extends the Exception class.
 */
package com.example.skgottalent.exceptions;

public class DataSaveException extends Exception {

    /**
     * Constructs a new DataSaveException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DataSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
