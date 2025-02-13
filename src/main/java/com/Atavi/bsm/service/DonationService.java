package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.DonationRequest;
import com.Atavi.bsm.responseDTO.DonationResponse;

public interface DonationService
{
    DonationResponse addDonation(DonationRequest donationRequest, int donationRequestId);
}
