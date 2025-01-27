package com.Atavi.bsm.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Atavi.bsm.enums.BloodGroup;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodSampleRequest {

    private BloodGroup bloodGroup;
    private int quantity;
    private boolean availability;
    private int emergencyUnits;
    private int availableUnits;
}
