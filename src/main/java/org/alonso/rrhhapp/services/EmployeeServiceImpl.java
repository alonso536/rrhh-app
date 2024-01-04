package org.alonso.rrhhapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Employee;
import org.alonso.rrhhapp.models.entities.Job;
import org.alonso.rrhhapp.models.exceptions.CityNotFoundException;
import org.alonso.rrhhapp.models.exceptions.EmployeeNotFoundException;
import org.alonso.rrhhapp.models.exceptions.JobNotFoundException;
import org.alonso.rrhhapp.repositories.AddressRepository;
import org.alonso.rrhhapp.repositories.CityRepository;
import org.alonso.rrhhapp.repositories.EmployeeRepository;
import org.alonso.rrhhapp.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        return employees.stream()
                .map((employee) -> buildEmployee(employee))
                .collect(Collectors.toList());
    }

    @Override
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
                .birthdate(createEmployeeDTO.getBirthdate())
                .boss(boss)
                .build();

        employee = employeeRepository.save(employee);
        return buildEmployee(employee);
    }

    @Override
    public List<Job> findJobs() {
        return (List<Job>) jobRepository.findAll();
    }

    @Override
    public List<City> findCities() {
        return (List<City>) cityRepository.findAll();
    }

    private EmployeeDTO buildEmployee(Employee employee) {
        Address addressDB = employee.getAddress();
        StringBuilder address = new StringBuilder(addressDB.getStreet())
                .append(" ")
                .append(addressDB.getNumber())
                .append(" ")
                .append(addressDB.getCity().getName());

        String boss = (employee.getBoss() != null)
                ? employee.getBoss().getName() + " " + employee.getBoss().getLastname()
                : null;

        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName() + " " + employee.getLastname())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .address(address.toString())
                .boss(boss)
                .job(employee.getJob().getName())
                .department(employee.getJob().getDepartment().getName())
                .birthdate(employee.getBirthdate())
                .hiredate(employee.getHiredate())
                .build();
    }
}
