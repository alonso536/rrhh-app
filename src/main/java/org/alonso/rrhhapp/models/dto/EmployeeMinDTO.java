package org.alonso.rrhhapp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeMinDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
}
