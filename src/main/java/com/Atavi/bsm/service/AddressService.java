package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.AddressRequest;
import com.Atavi.bsm.responseDTO.AddressResponse;

public interface AddressService {
    AddressResponse addHospitalAddress(AddressRequest addressRequest);

    AddressResponse addUserAddress(AddressRequest addressRequest);

    AddressResponse addBloodBankAddress(AddressRequest addressRequest,int bloodBankId);
}
