package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.Candidate;

import java.util.Optional;

public interface CandidateServiceInterface {

    public Candidate createCandidate(Candidate candidate);

    public Optional<Candidate> readCandidate(String id);

    public Candidate updateCandidate(String id, Candidate candidate);

    public Optional<Candidate> deleteCandidate(String id);

    public Candidate updateVoteCount(String id);
}
