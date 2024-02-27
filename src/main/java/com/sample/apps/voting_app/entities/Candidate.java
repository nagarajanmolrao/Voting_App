package com.sample.apps.voting_app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CandidateName", nullable = false)
    private String name;

    @Column(name="VoteCount")
    private Long voteCount;
}
