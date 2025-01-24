package com.Atavi.bsm.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BloodBankRequest {
    private String bloodBankName;
    private int emergencyUnitCount;
}
