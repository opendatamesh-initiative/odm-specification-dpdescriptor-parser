package org.opendatamesh.dpds.exceptions;

public class DeserializationException extends Exception {
    
    public DeserializationException(String message) {
        super(message);
    }

    public DeserializationException(String message, Throwable t) {
        super(message, t);
    }
}
