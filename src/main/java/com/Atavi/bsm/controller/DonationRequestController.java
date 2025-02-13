package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.DonatationRequestDto;
import com.Atavi.bsm.responseDTO.DonationRequestResponse;
import com.Atavi.bsm.service.DonationRequestService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DonationRequestController
{
    private final DonationRequestService donationRequestService;
    private  final RestResponseBuilder restResponseBuilder;


    @PostMapping("/donationRequest/{hospitalId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> createDonationRequestForHospital(@RequestBody DonatationRequestDto donationRequest, @PathVariable int hospitalId)
    {
        DonationRequestResponse donationResponse = donationRequestService.createDonationRequestForHospital(donationRequest,hospitalId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Donataion Request by Hospital added Successfully",donationResponse);
    }

    @PostMapping("/bloodBank-donationRequest/{bloodBankId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> createDonationRequestFromBloodBank(@RequestBody DonatationRequestDto donationRequest, @PathVariable int bloodBankId)
    {
        DonationRequestResponse donationResponse = donationRequestService.createDonationRequestFromBloodBank(donationRequest,bloodBankId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Donation Request by BloodBank added Successfully",donationResponse);
    }
}
