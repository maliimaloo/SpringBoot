package com.example.playerapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIExceptionResponseModel> handleException(APIException exception) {
        APIException.ErrorCode code = exception.getCode();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIExceptionResponseModel(code, code.getMessage()));
    }

    public record APIExceptionResponseModel(APIException.ErrorCode code, String message) {
    }
}