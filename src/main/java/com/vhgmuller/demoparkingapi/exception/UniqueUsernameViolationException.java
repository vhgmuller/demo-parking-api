package com.vhgmuller.demoparkingapi.exception;

public class UniqueUsernameViolationException extends RuntimeException {

    public UniqueUsernameViolationException(String message) {
        super(message);
    }
}
