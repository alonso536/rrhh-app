package org.alonso.rrhhapp.services;

import java.util.List;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;

public interface EmployeeService {
    List<EmployeeDTO> findAll();
}
