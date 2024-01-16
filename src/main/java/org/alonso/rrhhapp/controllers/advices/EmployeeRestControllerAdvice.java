package org.alonso.rrhhapp.controllers.advices;

import java.util.Arrays;

import org.alonso.rrhhapp.models.exceptions.CityNotFoundException;
import org.alonso.rrhhapp.models.exceptions.EmployeeNotFoundException;
import org.alonso.rrhhapp.models.exceptions.JobNotFoundException;
import org.alonso.rrhhapp.models.responses.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class EmployeeRestControllerAdvice {
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleCityNotFoundException(CityNotFoundException e) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleJobNotFoundException(JobNotFoundException e) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setErrors(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setErrors(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setErrors(Arrays.asList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(Exception e) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrors(Arrays.asList("An error has occurred. Talk to the administrator"));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}