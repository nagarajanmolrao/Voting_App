package com.sample.apps.voting_app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse extends Exception{
    private int status;
    private String msg;
    private long timestamp;
}
