package org.alonso.rrhhapp.models.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message) {
        super(message);
    }
}
