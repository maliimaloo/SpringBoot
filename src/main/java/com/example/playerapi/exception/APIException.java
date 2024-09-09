package com.example.playerapi.exception;

public class APIException extends RuntimeException {
    private final ErrorCode code;

    public APIException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public enum ErrorCode {
        PLAYER_NOT_FOUND("Player not found"),
        PLAYER_ALREADY_EXISTS("Player already exists"),
        INTERNAL_ERROR("Internal server error");

        private final String message;

        ErrorCode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}