package com.Atavi.bsm.exceptionHandler;

import com.Atavi.bsm.exception.BloodBankNotFoundException;
import com.Atavi.bsm.util.ErrorStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class UserNameNotFoundHandler {
    private final RestResponseBuilder restResponseBuilder;

    public <T> ResponseEntity<ErrorStructure<T>> handleUserNameNotFound(BloodBankNotFoundException ex){

        return restResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(), (T) "User not found by given ID");
    }


}
