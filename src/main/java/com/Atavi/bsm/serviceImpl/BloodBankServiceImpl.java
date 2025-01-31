package com.Atavi.bsm.serviceImpl;


import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.exception.BloodBankNotFoundException;
import com.Atavi.bsm.repository.AdminRepository;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.repository.BloodSampleRepositiry;
import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.exception.AdminNotFoundException;
import com.Atavi.bsm.responseDTO.AddressResponse;
import com.Atavi.bsm.responseDTO.BloodBankPageResponse;
import com.Atavi.bsm.responseDTO.BloodBankResponse;

import com.Atavi.bsm.responseDTO.BloodSampleResponse;
import com.Atavi.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService
{

    private final BloodBankRepository bloodBankRepository;
    private final AdminRepository adminRepository;
    private final BloodSampleRepositiry bloodSampleRepositiry;

    private BloodBank mapBloodBankToBloodBankRequest(BloodBankRequest bloodBankRequest, BloodBank bloodBank) {
        bloodBank.setBloodBankName(bloodBankRequest.getBloodBankName());
        bloodBank.setEmergencyUnitCount(bloodBankRequest.getEmergencyUnitCount());
        return bloodBank;
    }

    private BloodBankResponse mapBloodBankToBloodBankResponse(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bloodBankId(bloodBank.getBloodBankId())
                .bloodBankName(bloodBank.getBloodBankName())
                .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                .build();
    }


// T            .bloodBankName(bloodBank.getBloodBankName())                           his is to add only Blood Bank details without admin
//              .emergencyUnitCount(bloodBank.getEmergencyUnitCount())                   @Override
//              .build();                                                                public BloodBankResponse addBloodBank(BloodBankRequest bloodBankRequest) {
//  }                                                                                       BloodBank bloodBank = mapBloodBankToBloodBankRequest(bloodBankRequest,new BloodBank());
//       bloodBankRepository.save(bloodBank);//      return mapBloodBankToBloodBankResponse(bloodBank);
//    }
                   private AddressResponse mapAddressResponseTOPageResponse(BloodBank bloodBank)
                   {
                     return  AddressResponse.builder()
                              .addressId(bloodBank.getAddress().getAddressId())
                               .addressLine(bloodBank.getAddress().getAddressLine())
                              .landmark(bloodBank.getAddress().getLandmark())
                              .city(bloodBank.getAddress().getCity())
                              .state(bloodBank.getAddress().getState())
                              .country(bloodBank.getAddress().getCountry())
                              .postalCode(bloodBank.getAddress().getPostalCode())
                              .build() ;
                   }

                   private BloodSampleResponse mapBloodSampleResponseTOPageResponse(BloodSample bloodSample) {

                       return BloodSampleResponse.builder()
                               .sampleId(bloodSample.getSampleId())
                               .bloodGroup(bloodSample.getBloodGroup())
                               .availability(bloodSample.isAvailability())
                               .availableUnits(bloodSample.getAvailableUnits())
                               .quantity(bloodSample.getQuantity())
                               .build();

                   }

// This method Associates Admin and creates Blood Bank entity
    @Override
     public BloodBankResponse addBloodBankWithAdmin(BloodBankRequest bloodBankRequest,int adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);

        if (admin.isEmpty())
            throw new AdminNotFoundException("Admin Not found for the given Admin Id");

        List<Admin> adminList = new ArrayList<>();
        adminList.add(admin.get());
        BloodBank bloodBank = BloodBank.builder()

                .admin(adminList)
                .bloodBankName(bloodBankRequest.getBloodBankName())
                .emergencyUnitCount(bloodBankRequest.getEmergencyUnitCount())
                .build();

        bloodBankRepository.save(bloodBank);
        return mapBloodBankToBloodBankResponse(bloodBank);
    }
    @Override
    public BloodBankResponse findBloodBankById(int bloodBankId) {
        Optional<BloodBank> bloodBankResult = bloodBankRepository.findById(bloodBankId);

        if(bloodBankResult.isEmpty())
            throw new BloodBankNotFoundException("Blood Bank not found from the given ID");

        BloodBank bloodBank = bloodBankResult.get();

        return mapBloodBankToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodBankId)
    {

        Optional<BloodBank> bloodBankResult = bloodBankRepository.findById(bloodBankId);

        if(bloodBankResult.isEmpty())
            throw new BloodBankNotFoundException("Blood Bank not found from the given ID");

        BloodBank existingBloodBank = bloodBankResult.get();
       // existingBloodBank = mapBloodBankToBloodBankRequest(bloodBankRequest,existingBloodBank);
        mapBloodBankToBloodBankRequest(bloodBankRequest,existingBloodBank);
        bloodBankRepository.save(existingBloodBank);

        return mapBloodBankToBloodBankResponse(existingBloodBank);
    }
        //This Method returns only the BloodBanks List with data in Blood Bank entity
//    @Override
//    //public List<BloodBankResponse> findBloodBanks(List<String> city,BloodGroup bloodGroup)
//    public List<BloodBankResponse> findBloodBanks(List<String> city, BloodGroup bloodGroup, int pageNumber, int pageSize) {
//        {
//            //List<BloodBank> bloodBankList =  bloodBankRepository.findAll();
//            //  List<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityIn(cities);
//
//            List<BloodGroup> bloodGroups = new ArrayList<>();
//            bloodGroups.add(bloodGroup);
//
//            // List<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityInAndBloodSample_BloodGroupIn(city,bloodGroups);
//
//
//            Pageable pageable = PageRequest.of(pageNumber, pageSize);
//
//            Page<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityInAndBloodSample_BloodGroupIn(city, bloodGroups, pageable);
//            List<BloodBank> bloodBankList1 = bloodBankList.toList();
//            List<BloodBankResponse> list = new ArrayList<>();
//
//
//            for (BloodBank bloodBank : bloodBankList1) {
//                BloodBankResponse bloodBankLst = BloodBankResponse.builder()
//                        .bloodBankId(bloodBank.getBloodBankId())
//                        .bloodBankName(bloodBank.getBloodBankName())
//                        .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
//                        .build();
//
//                list.add(bloodBankLst);
//            }
//
//            return list;
//        }
//    }

     // This method returns the Structured Response of BloodBank name with Sample and Address Details in detail
    public Page<BloodBank> findAllBloodBanks(List<String> city, BloodGroup bloodGroup, int pageNumber, int pageSize)
    {

          //List<BloodBank> bloodBankList =  bloodBankRepository.findAll();
            //  List<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityIn(cities);
                            // List<BloodBank> bloodBankList = bloodBankRepository.findByAddress_CityInAndBloodSample_BloodGroupIn(city,bloodGroups);
            List<BloodGroup> bloodGroupsList = new ArrayList<>();
            bloodGroupsList.add(bloodGroup);
            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            Page<BloodBank> bloodBankListPage = bloodBankRepository.findByAddress_CityInAndBloodSample_BloodGroupIn(city, bloodGroupsList, pageable);

            return bloodBankListPage;

        }

    public List<BloodBankPageResponse> generateBloodBankPageResponse(Page<BloodBank> bloodBankListPage, BloodGroup bloodGroup)
    {
        List<BloodGroup> bloodGroupsList = new ArrayList<>();
        bloodGroupsList.add(bloodGroup);

        List<BloodBank> bloodBankList = bloodBankListPage.toList();

        List<BloodBankPageResponse> bloodBankPageResponse = new ArrayList<>();

        for (BloodBank bloodBank : bloodBankList)
        {
            List<BloodSample> bloodSamples = bloodSampleRepositiry.findByBloodBankAndBloodGroupIn(bloodBank, bloodGroupsList);
               List<BloodSampleResponse> responseList = new ArrayList<>();

                             for(BloodSample bloodSample : bloodSamples)
                             {
                               BloodSampleResponse sampleResponse= mapBloodSampleResponseTOPageResponse(bloodSample);

                                  responseList.add(sampleResponse);
                             }

                           BloodBankPageResponse PageResponse = BloodBankPageResponse.builder()
                                   .bloodBankId(bloodBank.getBloodBankId())
                                   .name(bloodBank.getBloodBankName())
                                   .address(mapAddressResponseTOPageResponse(bloodBank))
                                   .sample(responseList)
                                   .build();
                           bloodBankPageResponse.add(PageResponse);
        }
        return bloodBankPageResponse;
    }

}





































































