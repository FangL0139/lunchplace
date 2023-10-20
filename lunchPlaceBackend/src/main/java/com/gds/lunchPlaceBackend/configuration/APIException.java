package com.gds.lunchPlaceBackend.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIException extends Exception {
    private final String errorCode;

    public APIException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
