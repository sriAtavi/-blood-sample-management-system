package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.entity.Address;
import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.BloodSample;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BloodBankRequest {
    private String bloodBankName;
    private int emergencyUnitCount;

    private List<Admin> admin;
    private List<BloodSample> bloodSample;
    private Address address;
}
