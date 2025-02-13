package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.*;
import com.Atavi.bsm.exception.AdminNotFoundException;
import com.Atavi.bsm.repository.*;
import com.Atavi.bsm.requestDTO.AddressRequest;
import com.Atavi.bsm.responseDTO.AddressResponse;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AdminRepository adminRepository;
    private final AddressRespository addressRespository;
    private final BloodBankRepository bloodBankRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;

    private Address mapAddressToAddressRequest(AddressRequest addressRequest,Address address) {
        address.setAddressLine(addressRequest.getAddressLine());
        address.setLandmark(addressRequest.getLandmark());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCity(addressRequest.getCity());
        return address;
    }
//
//    private Address mapAddressToBloodBankAddressRequest(AddressRequest addressRequest,Address address) {
//        address.setAddressLine(addressRequest.getAddressLine());
//        address.setLandmark(addressRequest.getLandmark());
//        address.setCity(addressRequest.getCity());
//        address.setState(addressRequest.getState());
//        address.setCountry(addressRequest.getCountry());
//        address.setPostalCode(addressRequest.getPostalCode());
//        address.setCity(addressRequest.getCity());
//        address.setBloodBank(addressRequest.getBloodBank());
//        return address;
//    }
//
//    private Address mapAddressToHospitalAddressRequest(AddressRequest addressRequest,Address address) {
//        address.setAddressLine(addressRequest.getAddressLine());
//        address.setLandmark(addressRequest.getLandmark());
//        address.setCity(addressRequest.getCity());
//        address.setState(addressRequest.getState());
//        address.setCountry(addressRequest.getCountry());
//        address.setPostalCode(addressRequest.getPostalCode());
//        address.setCity(addressRequest.getCity());
//        address.setHospital(addressRequest.getHospital());
//        return address;
//    }
    private AddressResponse mapToAddressToAddressResponse(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landmark(address.getLandmark())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

//    private AddressResponse mapToAddressToHospitalAddressResponse(Address address) {
//        return AddressResponse.builder()
//                .addressId(address.getAddressId())
//                .addressLine(address.getAddressLine())
//                .landmark(address.getLandmark())
//                .city(address.getCity())
//                .state(address.getState())
//                .country(address.getCountry())
//                .postalCode(address.getPostalCode())
//                .hospital(address.getHospital())
//                .build();
//    }
//
//    private AddressResponse mapToAddressToBloodBankAddressResponse(Address address) {
//        return AddressResponse.builder()
//                .addressId(address.getAddressId())
//                .addressLine(address.getAddressLine())
//                .landmark(address.getLandmark())
//                .city(address.getCity())
//                .state(address.getState())
//                .country(address.getCountry())
//                .postalCode(address.getPostalCode())
//                .bloodBank(address.getBloodBank())
//                .build();
//    }


//    @Override
//    public AddressResponse addHospitalAddress(AddressRequest addressRequest) {
//        Address hospitalAddress = mapAddressToHospitalAddressRequest(addressRequest,new Address());
//        return mapToAddressToHospitalAddressResponse(hospitalAddress);
//    }
//
//    @Override
//    public AddressResponse addUserAddress(AddressRequest addressRequest) {
//        Address userAddress = mapAddressToUserAddressRequest(addressRequest,new Address());
//        return mapToAddressToUserAddressResponse(userAddress);
//    }

    @Override
    public AddressResponse addHospitalAddress(AddressRequest addressRequest,int hospitalId) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);

        if (hospitalOptional.isEmpty())
            throw new AdminNotFoundException("Hospital Not found for the given Hospital Id");


        Hospital hospitalDetails = hospitalOptional.get();
        Address hospitalAddress = mapAddressToAddressRequest(addressRequest,new Address());
        hospitalDetails.setAddress(hospitalAddress);

        addressRespository.save(hospitalAddress);
        return mapToAddressToAddressResponse(hospitalAddress);
    }

    @Override
    public AddressResponse addUserAddress(AddressRequest addressRequest,int userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty())
            throw new AdminNotFoundException("user Not found for the given User Id");


        User userDetails = userOptional.get();
        Address userAddress = mapAddressToAddressRequest(addressRequest,new Address());
        userDetails.setAddress(userAddress);

        addressRespository.save(userAddress);
        return mapToAddressToAddressResponse(userAddress);

    }

    @Override
    public AddressResponse addBloodBankAddress(AddressRequest addressRequest,int bloodBankId) {

        Optional<BloodBank> bloodBank = bloodBankRepository.findById(bloodBankId);

        if (bloodBank.isEmpty())
            throw new AdminNotFoundException("BloodBank Not found for the given bloodBank Id");


        BloodBank bloodBankDetails = bloodBank.get();
        Address bloodBankAddress = mapAddressToAddressRequest(addressRequest,new Address());
        bloodBankDetails.setAddress(bloodBankAddress);

        addressRespository.save(bloodBankAddress);
        return mapToAddressToAddressResponse(bloodBankAddress);



    }
}
