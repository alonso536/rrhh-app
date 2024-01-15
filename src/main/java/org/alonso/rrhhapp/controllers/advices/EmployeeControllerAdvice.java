package org.alonso.rrhhapp.controllers.advices;

import org.alonso.rrhhapp.models.exceptions.CityNotFoundException;
import org.alonso.rrhhapp.models.exceptions.EmployeeNotFoundException;
import org.alonso.rrhhapp.models.exceptions.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCityNotFoundException(CityNotFoundException e, Model model) {
        model.addAttribute("title", "Error 400: Bad Request");
        model.addAttribute("error", e.getMessage());

        return "error";
    }

    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJobNotFoundException(JobNotFoundException e, Model model) {
        model.addAttribute("title", "Error 400: Bad Request");
        model.addAttribute("error", e.getMessage());

        return "error";
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException e, Model model) {
        model.addAttribute("title", "Error 404: Not found");
        model.addAttribute("error", e.getMessage());

        return "error";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, Model model) {
        model.addAttribute("title", "Error 404: Not found");
        model.addAttribute("error", "The id must be a number");

        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        model.addAttribute("title", "Error 500: Internal Server Error");
        model.addAttribute("error", "An error has occurred. Talk to the administrator.");

        return "error";
    }
}