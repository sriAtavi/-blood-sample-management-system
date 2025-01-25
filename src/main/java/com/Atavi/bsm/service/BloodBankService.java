package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.responseDTO.BloodBankResponse;

import java.util.List;

public interface BloodBankService {
    BloodBankResponse addBloodBankWithAdmin(BloodBankRequest bloodBankRequest,int adminId);

    BloodBankResponse findBloodBankById(int bloodBankId);

    BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodBankId);

    List<BloodBankResponse> findBloodBanks();
}
