package org.alonso.rrhhapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping({ "/", "", "/index" })
    public String index(Model model) {
        List<EmployeeDTO> employees = employeeService.findAll();
        model.addAttribute("title", "Employees list");
        model.addAttribute("employees", employees);

        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        CreateEmployeeDTO employee = new CreateEmployeeDTO();
        model.addAttribute("title", "Add Employee");
        model.addAttribute("employee", employee);
        model.addAttribute("jobs", employeeService.findJobs());
        model.addAttribute("cities", employeeService.findCities());

        return "create";
    }

    @PostMapping("/store")
    public String store(@Valid CreateEmployeeDTO employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

            model.addAttribute("title", "Add Employee");
            model.addAttribute("errors", errors);
            model.addAttribute("employee", employee);
            model.addAttribute("jobs", employeeService.findJobs());
            model.addAttribute("cities", employeeService.findCities());

            return "create";
        }

        employeeService.save(employee);
        return "redirect:/index";
    }
}
