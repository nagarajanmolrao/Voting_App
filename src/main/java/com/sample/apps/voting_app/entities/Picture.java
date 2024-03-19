package com.sample.apps.voting_app.entities;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.UUIDGenerator;

import java.util.UUID;

@Entity

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Table(name="Picture")
public class Picture {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "FileType", nullable=false)
    private String fileType;

    @Column(name = "Type", nullable = false)
    private String type;

    @Lob
    @Column(name = "imageData", length = 100000)
    private byte[] imageData;

    @Column(name="LinkedId")
    private String linkedId;

    @Column(name="Tag")
    private String tag;
}
