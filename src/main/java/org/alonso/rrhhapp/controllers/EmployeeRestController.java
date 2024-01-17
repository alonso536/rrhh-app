package org.alonso.rrhhapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.responses.EmployeeResponse;
import org.alonso.rrhhapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> index() {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> index(@PathVariable Integer page) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.findAll(PageRequest.of(page, 12)));

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody CreateEmployeeDTO createEmployeeDTO, BindingResult result) {
        EmployeeResponse response = new EmployeeResponse();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map((error) -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
        }

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setPayload(employeeService.save(createEmployeeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateEmployeeDTO updateEmployeeDTO, BindingResult result,
            @PathVariable Long id) {
        EmployeeResponse response = new EmployeeResponse();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map((error) -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
        }

        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.update(updateEmployeeDTO, id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        EmployeeResponse response = new EmployeeResponse();

        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> jobs() {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.findJobs());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/cities")
    public ResponseEntity<?> cities() {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(employeeService.findCities());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}