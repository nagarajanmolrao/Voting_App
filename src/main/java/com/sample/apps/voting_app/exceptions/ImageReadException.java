package com.sample.apps.voting_app.exceptions;

import java.io.Serial;

public class ImageReadException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 8421506804341692775L;

    public ImageReadException(String message) {
        super(message);
    }
}
