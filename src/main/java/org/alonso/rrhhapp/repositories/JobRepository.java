package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
