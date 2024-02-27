package com.sample.apps.voting_app.repositories;

import com.sample.apps.voting_app.entities.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
}
