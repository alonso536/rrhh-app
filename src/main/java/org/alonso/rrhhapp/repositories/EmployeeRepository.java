package org.alonso.rrhhapp.repositories;

import java.util.List;

import org.alonso.rrhhapp.models.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.boss IS NULL")
    List<Employee> findBosses();
}
