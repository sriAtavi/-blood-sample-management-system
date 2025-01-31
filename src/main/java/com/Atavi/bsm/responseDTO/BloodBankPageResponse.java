package com.Atavi.bsm.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BloodBankPageResponse {
    private final int bloodBankId;
    private final String name;
   private final  AddressResponse address;
   private final List<BloodSampleResponse> sample;
}
