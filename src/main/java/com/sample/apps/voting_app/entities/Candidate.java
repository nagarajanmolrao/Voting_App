package com.sample.apps.voting_app.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Candidate")
public class Candidate {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="CandidateName", nullable = false)
    private String name;

    @Column(name="VoteCount")
    private Long voteCount;
}
