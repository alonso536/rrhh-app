package org.alonso.rrhhapp.models.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {
        super(message);
    }
}
