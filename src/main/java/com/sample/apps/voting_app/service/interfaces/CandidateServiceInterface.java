package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.Candidate;

import java.util.Optional;

public interface CandidateServiceInterface {

    public Candidate createCandidate(Candidate candidate);

    public Candidate readCandidate(Long id);

    public Candidate updateCandidate(Long id, Candidate candidate);

    public Candidate deleteCandidate(Long id);
}
