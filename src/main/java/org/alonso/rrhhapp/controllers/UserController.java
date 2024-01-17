package org.alonso.rrhhapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.CreateUserDTO;
import org.alonso.rrhhapp.models.responses.EmployeeResponse;
import org.alonso.rrhhapp.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> index() {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(userService.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> index(@PathVariable Integer page) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(userService.findAll(PageRequest.of(page, 10)));

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> store(@Valid @RequestBody CreateUserDTO createUserDTO, BindingResult result) {
        EmployeeResponse response = new EmployeeResponse();
        String confirmPassword = (createUserDTO.getConfirmPassword() != null) ? createUserDTO.getConfirmPassword() : "";

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map((error) -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            if (createUserDTO.getPassword() != null
                    && !createUserDTO.getPassword().equals(confirmPassword)) {
                errors.add("Passwords must matches");
            }

            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
        }

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setPayload(userService.save(createUserDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest request) {
        EmployeeResponse response = new EmployeeResponse();
        String token = request.getHeader("Authorization").substring(7);

        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(userService.checkAuth(token));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        EmployeeResponse response = new EmployeeResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(userService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        EmployeeResponse response = new EmployeeResponse();

        response.setStatusCode(HttpStatus.OK.value());
        response.setPayload(userService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
