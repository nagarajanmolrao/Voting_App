package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Candidate;
import com.sample.apps.voting_app.repositories.CandidateRepository;
import com.sample.apps.voting_app.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CandidateService implements CandidateServiceInterface {

    @Autowired
    CandidateRepository candidateRepo;
    @Override
    public Candidate createCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Optional<Candidate> readCandidate(Long id) {
        return Optional.empty();
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate candidate) {
        return null;
    }

    @Override
    public Optional<Candidate> deleteCandidate(Long id) {
        return Optional.empty();
    }
}
