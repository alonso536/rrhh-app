package org.alonso.rrhhapp.models.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String boss;
    private String job;
    private String department;
    private LocalDate birthdate;
    private LocalDate hiredate;
}
