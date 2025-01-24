package com.Atavi.bsm.exceptionHandler;

import com.Atavi.bsm.util.ErrorStructure;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
@AllArgsConstructor
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {
    /*
    handleMethodArgumentNotValid is method from MethodArgumentNotValidException class which returns the Exception details when a Bad Request comes
    in Field Exception level.
    So we are overriding this method in our class to provide exception details to Client when Field Validation fails
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> objectErrors = ex.getAllErrors();

        List<FieldErrorStructure> errors = new ArrayList<>();

        for (ObjectError objectError : objectErrors) {
            FieldError fieldError = (FieldError) objectError;

            errors.add(FieldErrorStructure.builder()
                    .field(fieldError.getField())
                    .defaultMessage(fieldError.getDefaultMessage())
                    .rejectedValue(fieldError.getRejectedValue())
                    .build());
        }
        ErrorStructure<List<FieldErrorStructure>> error = new ErrorStructure<>();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Invalid Input request");
        error.setRootCause(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

        @Getter
        @AllArgsConstructor
        @Builder
        @NoArgsConstructor
        public static class FieldErrorStructure {
            private String field;
            private Object rejectedValue;
            private String defaultMessage;

            public void setRejectedValue(Object rejectedValue) {
                this.rejectedValue = rejectedValue;
            }

            public void setField(String field) {
                this.field = field;
            }

            public void setDefaultMessage(String defaultMessage) {
                this.defaultMessage = defaultMessage;
            }
        }
    }

