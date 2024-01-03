package org.alonso.rrhhapp.repositories;

import org.alonso.rrhhapp.models.entities.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {

}
