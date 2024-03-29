package org.alonso.rrhhapp.services;

import static org.alonso.rrhhapp.models.helpers.EmployeeHelper.buildEmployee;
import static org.alonso.rrhhapp.models.helpers.EmployeeHelper.formatBirthdate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeMinDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Employee;
import org.alonso.rrhhapp.models.entities.Job;
import org.alonso.rrhhapp.models.exceptions.CityNotFoundException;
import org.alonso.rrhhapp.models.exceptions.EmailUniqueException;
import org.alonso.rrhhapp.models.exceptions.EmployeeNotFoundException;
import org.alonso.rrhhapp.models.exceptions.JobNotFoundException;
import org.alonso.rrhhapp.repositories.AddressRepository;
import org.alonso.rrhhapp.repositories.CityRepository;
import org.alonso.rrhhapp.repositories.EmployeeRepository;
import org.alonso.rrhhapp.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findEmployees();

        return employees.stream()
                .map((employee) -> buildEmployee(employee))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeMinDTO> findAll(Pageable page) {
        return employeeRepository.findEmployees(page);
    }

    @Override
    @Transactional
    public EmployeeDTO save(CreateEmployeeDTO createEmployeeDTO) {
        Job job = jobRepository.findById(createEmployeeDTO.getJob())
                .orElseThrow(() -> new JobNotFoundException("The job entered is not found"));

        Employee boss = employeeRepository.findBosses()
                .stream()
                .filter((b) -> b.getJob().getDepartment().equals(job.getDepartment()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("The employee entered is not found"));

        City city = cityRepository.findById(createEmployeeDTO.getCity())
                .orElseThrow(() -> new CityNotFoundException("The city entered is not found"));

        Address address = Address.builder()
                .street(createEmployeeDTO.getStreet())
                .number(createEmployeeDTO.getNumber())
                .city(city)
                .build();

        address = addressRepository.save(address);

        Employee employee = Employee.builder()
                .name(createEmployeeDTO.getName())
                .lastname(createEmployeeDTO.getLastname())
                .email(createEmployeeDTO.getEmail())
                .phone(createEmployeeDTO.getPhone())
                .address(address)
                .job(job)
                .birthdate(formatBirthdate(createEmployeeDTO.getBirthdate()))
                .boss(boss)
                .build();

        try {
            employee = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new EmailUniqueException("The email entered is in use");
        }

        return buildEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findOneById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("The employee entered is not found"));

        return buildEmployee(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO update(UpdateEmployeeDTO updateEmployeeDTO, Long id) {
        Employee employee = employeeRepository.findOneById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("The employee entered is not found"));

        employee.setName(updateEmployeeDTO.getName());
        employee.setLastname(updateEmployeeDTO.getLastname());
        employee.setEmail(updateEmployeeDTO.getEmail());
        employee.setPhone(updateEmployeeDTO.getPhone());

        try {
            employee = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new EmailUniqueException("The email entered is in use");
        }

        return buildEmployee(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO delete(Long id) {
        Employee employee = employeeRepository.findOneById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("The employee entered is not found"));

        employee.setFiredate(LocalDate.now());
        employee = employeeRepository.save(employee);

        return buildEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Job> findJobs() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> findCities() {
        return cityRepository.findAll();
    }
}
