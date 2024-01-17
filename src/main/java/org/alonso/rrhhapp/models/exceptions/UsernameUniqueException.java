package org.alonso.rrhhapp.models.exceptions;

public class UsernameUniqueException extends RuntimeException {
    public UsernameUniqueException(String message) {
        super(message);
    }
}
