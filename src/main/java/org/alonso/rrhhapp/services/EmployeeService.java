package org.alonso.rrhhapp.services;

import java.util.List;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Job;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO save(CreateEmployeeDTO createEmployeeDTO);

    List<Job> findJobs();

    List<City> findCities();

}
