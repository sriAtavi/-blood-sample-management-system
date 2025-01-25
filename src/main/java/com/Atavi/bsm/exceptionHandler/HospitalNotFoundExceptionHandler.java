package com.Atavi.bsm.exceptionHandler;

import com.Atavi.bsm.exception.HospitalNotFoundException;
import com.Atavi.bsm.util.ErrorStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HospitalNotFoundExceptionHandler {

    private final RestResponseBuilder restResponseBuilder;

    public <T> ResponseEntity<ErrorStructure<T>> handleHospitalNotFoundById(HospitalNotFoundException ex){

        return restResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(), (T)"Hospital not found by given ID");
    }
}
