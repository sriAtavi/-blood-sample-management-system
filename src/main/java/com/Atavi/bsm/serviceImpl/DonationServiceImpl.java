package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.exception.AdminNotFoundException;
import com.Atavi.bsm.repository.DonationRepository;
import com.Atavi.bsm.requestDTO.DonationRequest;
import com.Atavi.bsm.responseDTO.DonationResponse;
import com.Atavi.bsm.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService
{
    private final DonationRepository donationRepository;
    @Override
    public DonationResponse addDonation(DonationRequest donationRequest, int donationRequestId) {


//        Optional<Admin> admin = adminRepository.findById(adminId);
//
//        if (admin.isEmpty())
//            throw new AdminNotFoundException("Admin Not found for the given Admin Id");
//
//        List<Admin> adminList = new ArrayList<>();
//        adminList.add(admin.get());
//        BloodBank bloodBank = BloodBank.builder()
//
//                .admin(adminList)
//                .bloodBankName(bloodBankRequest.getBloodBankName())
//                .emergencyUnitCount(bloodBankRequest.getEmergencyUnitCount())
//                .build();
//
//        bloodBankRepository.save(bloodBank);
//        return mapBloodBankToBloodBankResponse(bloodBank);
        return null;
    }
}
