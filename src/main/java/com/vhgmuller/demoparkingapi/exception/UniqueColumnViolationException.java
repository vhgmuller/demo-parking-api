package com.vhgmuller.demoparkingapi.exception;

public class UniqueColumnViolationException extends RuntimeException {

    public UniqueColumnViolationException(String message) {
        super(message);
    }
}
