package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.DonatationRequestDto;
import com.Atavi.bsm.responseDTO.DonationRequestResponse;

public interface DonationRequestService
{
    DonationRequestResponse createDonationRequestForHospital(DonatationRequestDto donationRequest, int hospitalId);

    DonationRequestResponse createDonationRequestFromBloodBank(DonatationRequestDto donationRequest, int bloodBankId);
}
