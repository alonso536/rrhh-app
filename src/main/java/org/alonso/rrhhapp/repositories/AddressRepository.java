package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
