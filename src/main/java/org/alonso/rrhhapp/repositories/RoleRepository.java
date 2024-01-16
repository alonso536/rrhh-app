package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
