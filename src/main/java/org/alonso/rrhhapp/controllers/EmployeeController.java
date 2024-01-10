package org.alonso.rrhhapp.controllers;

import static org.alonso.rrhhapp.models.helpers.EmployeeHelper.setUpdateEmployeeDTO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("title", "Add Employee");
        model.addAttribute("employee", employee);
        model.addAttribute("jobs", employeeService.findJobs());
        model.addAttribute("cities", employeeService.findCities());

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
            model.addAttribute("errors", errors);

            return "create";
        }

        try {
            employeeService.save(employee);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errors", Map.of("email", "The email entered is in use"));
            return "create";
        }

        return "redirect:/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        EmployeeDTO employee = employeeService.findById(id);
        model.addAttribute("title", employee.getName());
        model.addAttribute("employee", employee);

        return "show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        UpdateEmployeeDTO employee = setUpdateEmployeeDTO(employeeService.findById(id));
        model.addAttribute("title", "Edit Employee");
        model.addAttribute("employee", employee);
        model.addAttribute("id", id);

        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid UpdateEmployeeDTO employee, BindingResult result, @PathVariable Long id,
            Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

            model.addAttribute("title", "Edit Employee");
            model.addAttribute("errors", errors);
            model.addAttribute("employee", employee);
            model.addAttribute("id", id);

            return "edit";
        }

        employeeService.update(employee, id);
        return "redirect:/index";
    }

    @PostMapping("/destroy/{id}")
    public String destroy(@PathVariable Long id, Model model) {
        employeeService.delete(id);
        return "redirect:/index";
    }
}
