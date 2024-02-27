package com.sample.apps.voting_app.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateNotFoundException extends Exception{
    private int status;
    private String msg;
    private long timestamp;
}
