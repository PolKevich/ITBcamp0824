package com.PolKevich.ITBcamp0824.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
@ControllerAdvice
public class ApplicationException {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

        String message = "Unacceptable JSON " + exception.getMessage();

        String errorDetails = message;

        if (exception.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) exception.getCause();
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                errorDetails = String.
                        format("Invalid enum value: '%s' for the field: '%s'. The value must be one of: %s.",
                             ifx.getValue(), ifx.getPath().get(ifx.getPath().size() - 1).
                                  getFieldName(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", errorDetails), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());

        messages.addAll(
                constraintViolations.stream()
                        .map(
                                constraintViolation ->
                                        String.format(
                                                "%s value '%s' %s",
                                                constraintViolation.getPropertyPath(),
                                                constraintViolation.getInvalidValue(),
                                                constraintViolation.getMessage()))
                        .collect(Collectors.toList()));

        log.warn("Bad request: {}", messages);

        return new ResponseEntity<>(Collections.singletonMap("error", messages), BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(

            MethodArgumentNotValidException e) {

        Map<String, String> messages = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {

            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("Bad request: {}", messages);

        return new ResponseEntity<>(Collections.singletonMap("error", messages), HttpStatus.BAD_REQUEST);
    }
}
