package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
