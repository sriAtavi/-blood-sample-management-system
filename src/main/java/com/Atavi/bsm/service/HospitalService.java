package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.HospitalRequest;
import com.Atavi.bsm.responseDTO.HospitalResponse;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest hospitalRequest);

    HospitalResponse findByHospitalId(int hospitalId);

    HospitalResponse updateHospital(HospitalRequest hospitalRequest, int hospitalId);

  //  HospitalResponse addHospitalAdmin(HospitalRequest hospitalRequest,int userId);

    HospitalResponse addHospitalAdmin(HospitalRequest hospitalRequest,int adminId);
}
