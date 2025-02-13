package com.Atavi.bsm.controller;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.requestDTO.AddressRequest;
import com.Atavi.bsm.responseDTO.AddressResponse;
import com.Atavi.bsm.security.AuthUtil;
import com.Atavi.bsm.service.AddressService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AddressController {
    private final AuthUtil authUtil;
    private final AddressService addressService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/hospital-address/{hospitalId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addHospitalAddress(@RequestBody AddressRequest addressRequest, @PathVariable int hospitalId)
    {
        AddressResponse addressResponse = addressService.addHospitalAddress(addressRequest,hospitalId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Hospital Address Added Successfully",addressResponse);
    }

    @PostMapping("/user-address/{userId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addUserAddress(@RequestBody AddressRequest addressRequest, @PathVariable int userId)
    {
        AddressResponse addressResponse = addressService.addUserAddress(addressRequest,userId);
        return restResponseBuilder.success(HttpStatus.CREATED,"User Address Added Successfully",addressResponse);
    }

    @PostMapping("/blood-bank-address/{bloodBankId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addBloodBankAddress(@RequestBody AddressRequest addressRequest, @PathVariable int bloodBankId)
    {
        AddressResponse addressResponse = addressService.addBloodBankAddress(addressRequest,bloodBankId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Blood Bank Address Added Successfully",addressResponse);
    }
}
