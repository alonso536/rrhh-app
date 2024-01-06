package org.alonso.rrhhapp.models.dto;

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

    @NotNull(message = "The field phone is required")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "The field phone must be a number")
    private String phone;
}
