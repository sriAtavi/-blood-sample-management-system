package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.AddressRequest;
import com.Atavi.bsm.responseDTO.AddressResponse;

public interface AddressService {
    AddressResponse addHospitalAddress(AddressRequest addressRequest,int hospitalId);

    AddressResponse addUserAddress(AddressRequest addressRequest, int userId);

    AddressResponse addBloodBankAddress(AddressRequest addressRequest,int bloodBankId);
}
