package org.alonso.rrhhapp.models.helpers;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.Employee;

public class EmployeeHelper {

    public static UpdateEmployeeDTO setUpdateEmployeeDTO(EmployeeDTO employee) {
        return UpdateEmployeeDTO.builder()
                .name(employee.getName().split(" ")[0])
                .lastname(employee.getName().split(" ")[1])
                .phone(employee.getPhone())
                .build();
    }

    public static EmployeeDTO buildEmployee(Employee employee) {
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
