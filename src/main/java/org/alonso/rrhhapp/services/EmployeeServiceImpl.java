package org.alonso.rrhhapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.Employee;
import org.alonso.rrhhapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        return employees.stream()
                .map((employee) -> buildEmployee(employee))
                .collect(Collectors.toList());
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
