package com.vhgmuller.demoparkingapi.web.exception;

import com.vhgmuller.demoparkingapi.exception.UniqueUsernameViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UniqueUsernameViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueUsernameViolationException(RuntimeException ex, HttpServletRequest request) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
}
