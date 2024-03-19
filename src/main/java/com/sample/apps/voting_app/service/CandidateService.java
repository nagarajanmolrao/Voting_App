package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Candidate;
import com.sample.apps.voting_app.repositories.CandidateRepository;
import com.sample.apps.voting_app.service.interfaces.CandidateServiceInterface;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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
    public Optional<Candidate> readCandidate(String id) {
        return candidateRepo.findById(UUID.fromString(id));
    }

    @Override
    public Candidate updateCandidate(String id, Candidate candidate) {
        Optional<Candidate> candidateToUpdate = candidateRepo.findById(UUID.fromString(id));
        if(candidateToUpdate.isEmpty()){
            return null;
        }
        candidateToUpdate.get().setName(candidate.getName());
        return candidateRepo.save(candidateToUpdate.get());
    }

    @Override
    public Optional<Candidate> deleteCandidate(String id) {
        Optional<Candidate> deletedCandidate = candidateRepo.findById(UUID.fromString(id));
        if(deletedCandidate.isEmpty()){
            return Optional.empty();
        }
        candidateRepo.delete(deletedCandidate.get());
        return deletedCandidate;
    }

    @Override
    public Candidate updateVoteCount(String id) {
        Optional<Candidate> candidateToUpdate = candidateRepo.findById(UUID.fromString(id));
        if(candidateToUpdate.isEmpty()){
            return null;
        }
        candidateToUpdate.get().setVoteCount(candidateToUpdate.get().getVoteCount()+1);
        return candidateRepo.save(candidateToUpdate.get());
    }
}
