package org.alonso.rrhhapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeDTO {

    @NotBlank(message = "The field name is required")
    private String name;

    @NotBlank(message = "The field lastname is required")
    private String lastname;

    @NotBlank(message = "The field email is required")
    @Email(message = "The field email must be in a correct format")
    private String email;

    @NotNull(message = "The field phone is required")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "The field phone must be a number")
    private String phone;

    @NotNull(message = "The field birthdate is required")
    private String birthdate;
}
