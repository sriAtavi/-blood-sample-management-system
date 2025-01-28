package com.Atavi.bsm.responseDTO;

import com.Atavi.bsm.entity.Address;
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
