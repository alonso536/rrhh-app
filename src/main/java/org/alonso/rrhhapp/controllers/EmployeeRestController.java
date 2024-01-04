package org.alonso.rrhhapp.controllers;

import java.util.List;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> index() {
        return employeeService.findAll();
    }
}
