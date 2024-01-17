package org.alonso.rrhhapp.models.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDTO {

    @NotBlank(message = "The field username is required")
    private String username;

    @NotBlank(message = "The field email is required")
    @Email(message = "The field email must be in a correct format")
    private String email;

    @NotBlank(message = "The field password is required")
    private String password;
    private String confirmPassword;

    private List<String> roles;
}
