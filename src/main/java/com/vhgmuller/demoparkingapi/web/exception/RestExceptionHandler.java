package com.vhgmuller.demoparkingapi.web.exception;

import com.vhgmuller.demoparkingapi.exception.EntityNotFoundException;
import com.vhgmuller.demoparkingapi.exception.UniqueColumnViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorMessage> noResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, String.format("No resource found at '%s'.", ex.getResourcePath())));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, String.format(
                        "The value '%s' is invalid for parameter '%s'. Expecting '%s' type.",
                        ex.getValue(),
                        ex.getParameter().getParameterName(),
                        ex.getParameter().getParameterType().getSimpleName()
                )
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Field(s)", result));
    }

    @ExceptionHandler(UniqueColumnViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueUsernameViolationException(RuntimeException ex, HttpServletRequest request) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request) {
        log.error("Rest Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
}
