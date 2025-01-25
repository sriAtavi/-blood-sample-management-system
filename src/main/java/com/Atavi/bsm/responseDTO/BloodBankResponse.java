package com.Atavi.bsm.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankResponse {
    private int bloodBankId;
    private String bloodBankName;
    private int emergencyUnitCount;
}
