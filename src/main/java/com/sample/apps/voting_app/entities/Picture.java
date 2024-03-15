package com.sample.apps.voting_app.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "FileType", nullable=false)
    private String fileType;

    @Column(name = "Type", nullable = false)
    private String type;

    @Lob
    @Column(name = "imageData", length = 100000)
    private byte[] imageData;

    @Column(name="LinkedId")
    private Long linkedId;
}
