package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
