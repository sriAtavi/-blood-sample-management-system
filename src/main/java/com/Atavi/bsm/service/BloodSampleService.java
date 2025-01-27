package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.BloodSampleRequest;
import com.Atavi.bsm.responseDTO.BloodSampleResponse;

public interface BloodSampleService {
    BloodSampleResponse addBloodSample(BloodSampleRequest sampleRequest);
}
