package com.Atavi.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BloodBankNotFoundException extends RuntimeException {
    private final String message;
}
