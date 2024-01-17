package org.alonso.rrhhapp.repositories;

import java.util.List;
import java.util.Optional;

import org.alonso.rrhhapp.models.dto.EmployeeMinDTO;
import org.alonso.rrhhapp.models.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firedate IS NULL")
    List<Employee> findEmployees();

    @Query("SELECT new org.alonso.rrhhapp.models.dto.EmployeeMinDTO(e.id, e.name, e.lastname, e.email, e.phone) FROM Employee e WHERE e.firedate IS NULL")
    Page<EmployeeMinDTO> findEmployees(Pageable page);

    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.firedate IS NULL")
    Optional<Employee> findOneById(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.boss IS NULL")
    List<Employee> findBosses();
}
