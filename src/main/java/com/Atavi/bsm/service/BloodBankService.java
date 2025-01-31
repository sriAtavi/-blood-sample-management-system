package com.Atavi.bsm.service;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.responseDTO.BloodBankPageResponse;
import com.Atavi.bsm.responseDTO.BloodBankResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BloodBankService {
    BloodBankResponse addBloodBankWithAdmin(BloodBankRequest bloodBankRequest,int adminId);

    BloodBankResponse findBloodBankById(int bloodBankId);

    BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodBankId);

    //List<BloodBankResponse> findBloodBanks(List<String> city);

    //List<BloodBankPageResponse> findAllBloodBanks(List<String> city, BloodGroup bloodGroup, int pageNumber, int pageSize);

    Page<BloodBank> findAllBloodBanks(List<String> city, BloodGroup bloodGroup, int pageNumber, int pageSize);

    List<BloodBankPageResponse> generateBloodBankPageResponse(Page<BloodBank> bloodBankListPage, BloodGroup bloodGroupsList);
}
