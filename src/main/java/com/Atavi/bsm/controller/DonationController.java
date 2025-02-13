package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.BloodSampleRequest;
import com.Atavi.bsm.requestDTO.DonationRequest;
import com.Atavi.bsm.responseDTO.BloodSampleResponse;
import com.Atavi.bsm.responseDTO.DonationResponse;
import com.Atavi.bsm.service.DonationService;
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
public class DonationController
{
    private DonationService donationService;
    private RestResponseBuilder restResponseBuilder;


    @PostMapping("/donation/{donationRequestId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> addBloodSample(@RequestBody DonationRequest donationRequest, @PathVariable int donationRequestId)
    {
        DonationResponse donationResponse = donationService.addDonation(donationRequest,donationRequestId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Blood Sample added successfully",donationResponse);
    }
}
