package com.sample.apps.voting_app.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="email", unique = true, nullable = false)
    @Pattern(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Not a valid Email")
    private String email;

    @Column(name="Name", nullable = false)
    private String name;

    @Column(name="Password", nullable = false)
    private String password;

    @Column(name="PhoneNumber", unique = true, nullable = false)
    @Pattern(regexp = "^[+]91{1}[- ]\\d{10}$", message = "Not a valid Phone Number")
    private String phoneNumber;

    @Column(name="Status")
    private Boolean voteStatus = false;

    @Column(name="Role", nullable = false)
    private String role;
}
