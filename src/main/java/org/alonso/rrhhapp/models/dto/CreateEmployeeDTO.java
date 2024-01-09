package org.alonso.rrhhapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class CreateEmployeeDTO {

    @NotBlank(message = "The field name is required")
    private String name;

    @NotBlank(message = "The field lastname is required")
    private String lastname;

    @NotBlank(message = "The field email is required")
    @Email(message = "The field email must be in a correct format")
    private String email;

    @NotNull(message = "The field phone is required")
    @Size(min = 7, max = 15, message = "The field phone must be between 7 and 15 digits")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "The field phone must be a number")
    private String phone;

    @NotNull(message = "The field birthdate is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "The field birthdate must be in date format")
    private String birthdate;

    @NotNull(message = "The field job is required")
    @Positive(message = "The field job is not valid")
    private Long job;

    @NotBlank(message = "The field street is required")
    private String street;

    @NotNull(message = "The field number is required")
    @Positive(message = "The field number must be a positive number")
    private Integer number;

    @NotNull(message = "The field city is required")
    @Positive(message = "The field city is not valid")
    private Long city;
}
