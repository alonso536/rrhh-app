package org.alonso.rrhhapp.models.exceptions;

public class EmailUniqueException extends RuntimeException {
    public EmailUniqueException(String message) {
        super(message);
    }
}
