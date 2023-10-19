package com.gds.lunchPlaceBackend.configuration;

import com.gds.lunchPlaceBackend.dto.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonException {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<GeneralResponse> handleException(APIException e) {
        return ResponseEntity.badRequest().body(GeneralResponse.builder().errorCode(e.getErrorCode()).message(e.getMessage()).build());
    }
}
