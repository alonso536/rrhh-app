package org.alonso.rrhhapp.controllers;

import org.alonso.rrhhapp.models.responses.EmployeeResponse;
import org.alonso.rrhhapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        response.setPayload(employeeService.findAll(PageRequest.of(page, 8)));

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }
}
