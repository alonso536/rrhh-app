package org.alonso.rrhhapp.repositories;

import java.util.List;
import java.util.Optional;

import org.alonso.rrhhapp.models.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firedate IS NULL")
    List<Employee> findEmployees();

    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.firedate IS NULL")
    Optional<Employee> findOneById(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.boss IS NULL")
    List<Employee> findBosses();
}
