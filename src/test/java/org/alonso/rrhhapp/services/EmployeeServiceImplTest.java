package org.alonso.rrhhapp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.alonso.rrhhapp.models.helpers.DataTestHelper.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Job;
import org.alonso.rrhhapp.models.exceptions.CityNotFoundException;
import org.alonso.rrhhapp.models.exceptions.EmployeeNotFoundException;
import org.alonso.rrhhapp.models.exceptions.JobNotFoundException;
import org.alonso.rrhhapp.repositories.AddressRepository;
import org.alonso.rrhhapp.repositories.CityRepository;
import org.alonso.rrhhapp.repositories.EmployeeRepository;
import org.alonso.rrhhapp.repositories.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testFindAll() {
        when(employeeRepository.findEmployees()).thenReturn(EMPLOYEES_TEST);
        List<EmployeeDTO> employees = employeeService.findAll();

        assertNotNull(employees);
        assertEquals(2, employees.size());

        verify(employeeRepository, times(1)).findEmployees();
    }

    @Test
    void testFindById() {
        when(employeeRepository.findOneById(1L)).thenReturn(Optional.of(EMPLOYEES_TEST.get(0)));
        EmployeeDTO employee = employeeService.findById(1L);

        assertNotNull(employee);
        assertEquals(1L, employee.getId());

        verify(employeeRepository, times(1)).findOneById(1L);
    }

    @Test
    void testFindByIdNull() {
        when(employeeRepository.findOneById(5L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findById(5L);
        });

        verify(employeeRepository, times(1)).findOneById(5L);
    }

    @Test
    void testSave() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(JOBS_TEST.get(0)));
        when(cityRepository.findById(1L)).thenReturn(Optional.of(CITIES_TEST.get(0)));
        when(employeeRepository.findBosses()).thenReturn(EMPLOYEES_BOSSES_TEST);
        when(addressRepository.save(ADDRESS_TEST)).thenReturn(ADDRESS_TEST);
        when(employeeRepository.save(EMPLOYEE_TEST)).thenReturn(EMPLOYEE_TEST);

        EmployeeDTO employee = employeeService.save(CREATE_EMPLOYEE_DTO_TEST);
        assertNotNull(employee);
        assertTrue(employee.getAddress() instanceof String);
        assertTrue(employee.getBoss() instanceof String);
        assertTrue(employee.getBirthdate() instanceof LocalDate);
        assertNull(employee.getFiredate());

        verify(jobRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(1)).findBosses();
        verify(cityRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(1)).save(ADDRESS_TEST);
        verify(jobRepository, times(1)).findById(anyLong());
    }

    @Test
    void testSaveCityNotFound() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(JOBS_TEST.get(0)));
        when(employeeRepository.findBosses()).thenReturn(EMPLOYEES_BOSSES_TEST);
        when(cityRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(CityNotFoundException.class, () -> {
            employeeService.save(CREATE_EMPLOYEE_DTO_BAD_CITY_TEST);
        });

        verify(jobRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(1)).findBosses();
        verify(cityRepository, times(1)).findById(anyLong());
    }

    @Test
    void testSaveJobNotFound() {
        when(jobRepository.findById(52L)).thenReturn(Optional.empty());

        assertThrows(JobNotFoundException.class, () -> {
            employeeService.save(CREATE_EMPLOYEE_DTO_BAD_JOB_TEST);
        });

        verify(jobRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdate() {
        when(employeeRepository.findOneById(2L)).thenReturn(Optional.of(EMPLOYEES_TEST.get(1)));
        when(employeeRepository.save(EMPLOYEES_TEST.get(1))).thenReturn(EMPLOYEES_TEST.get(1));

        EmployeeDTO employee = employeeService.update(UPDATE_EMPLOYEE_DTO_TEST, 2L);
        assertNotNull(employee);
        assertEquals(2L, employee.getId());

        verify(employeeRepository, times(1)).findOneById(2L);
        verify(employeeRepository, times(1)).save(EMPLOYEES_TEST.get(1));
    }

    @Test
    void testUpdateNull() {
        when(employeeRepository.findOneById(5L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.update(UPDATE_EMPLOYEE_DTO_TEST, 5L);
        });

        verify(employeeRepository, times(1)).findOneById(5L);
    }

    @Test
    void testDelete() {
        when(employeeRepository.findOneById(2L)).thenReturn(Optional.of(EMPLOYEES_TEST.get(1)));
        when(employeeRepository.save(EMPLOYEES_TEST.get(1))).thenReturn(EMPLOYEE_FIRED_TEST);

        EmployeeDTO employee = employeeService.delete(2L);
        assertNotNull(employee);
        assertNotNull(employee.getFiredate());

        verify(employeeRepository, times(1)).findOneById(2L);
        verify(employeeRepository, times(1)).save(EMPLOYEES_TEST.get(1));
    }

    @Test
    void testDeleteNull() {
        when(employeeRepository.findOneById(5L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.delete(5L);
        });

        verify(employeeRepository, times(1)).findOneById(5L);
    }

    @Test
    void testFindJobs() {
        when(jobRepository.findAll()).thenReturn(JOBS_TEST);
        List<Job> jobs = employeeService.findJobs();

        assertNotNull(jobs);
        assertEquals(3, jobs.size());

        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void testFindCities() {
        when(cityRepository.findAll()).thenReturn(CITIES_TEST);
        List<City> cities = employeeService.findCities();

        assertNotNull(cities);
        assertEquals(3, cities.size());

        verify(cityRepository, times(1)).findAll();
    }
}
