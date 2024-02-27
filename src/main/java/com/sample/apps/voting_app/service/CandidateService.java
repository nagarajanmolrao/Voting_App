package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Candidate;
import com.sample.apps.voting_app.exceptions.CandidateNotFoundException;
import com.sample.apps.voting_app.repositories.CandidateRepository;
import com.sample.apps.voting_app.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService implements CandidateServiceInterface {

    private final CandidateRepository candidateRepo;

    @Autowired
    public CandidateService(CandidateRepository candidateRepo){
        this.candidateRepo = candidateRepo;
    }
    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    @Override
    public Candidate readCandidate(Long id) {
        Optional<Candidate> tempCandidate = candidateRepo.findById(id);
        if (tempCandidate.isPresent()){
            return tempCandidate.get();
        }
        throw new CandidateNotFoundException();
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
