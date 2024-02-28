package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Candidate;
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
    public Optional<Candidate> readCandidate(Long id) {
        return candidateRepo.findById(id);
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate candidate) {
        Optional<Candidate> candidateToUpdate = candidateRepo.findById(id);
        if(candidateToUpdate.isEmpty()){
            return null;
        }
        candidateToUpdate.get().setName(candidate.getName());
        return candidateRepo.save(candidateToUpdate.get());
    }

    @Override
    public Optional<Candidate> deleteCandidate(Long id) {
        Optional<Candidate> deletedCandidate = candidateRepo.findById(id);
        if(deletedCandidate.isEmpty()){
            return Optional.empty();
        }
        candidateRepo.delete(deletedCandidate.get());
        return deletedCandidate;
    }

    @Override
    public Candidate updateVoteCount(Long id) {
        Optional<Candidate> candidateToUpdate = candidateRepo.findById(id);
        if(candidateToUpdate.isEmpty()){
            return null;
        }
        candidateToUpdate.get().setVoteCount(candidateToUpdate.get().getVoteCount()+1);
        return candidateRepo.save(candidateToUpdate.get());
    }
}
