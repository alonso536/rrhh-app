package org.alonso.rrhhapp.services;

import java.util.List;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    Page<EmployeeDTO> findAll(Pageable page);

    EmployeeDTO save(CreateEmployeeDTO createEmployeeDTO);

    EmployeeDTO findById(Long id);

    EmployeeDTO update(UpdateEmployeeDTO updateEmployeeDTO, Long id);

    EmployeeDTO delete(Long id);

    List<Job> findJobs();

    List<City> findCities();

}
