package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.entity.Hospital;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.AdminType;
import com.Atavi.bsm.enums.UserRole;
import com.Atavi.bsm.exception.AdminNotFoundException;
import com.Atavi.bsm.exception.HospitalNotFoundException;
import com.Atavi.bsm.repository.AdminRepository;
import com.Atavi.bsm.repository.HospitalRepository;
import com.Atavi.bsm.repository.UserRepository;
import com.Atavi.bsm.requestDTO.HospitalRequest;
import com.Atavi.bsm.responseDTO.HospitalResponse;
import com.Atavi.bsm.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {


    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    private Hospital mapHospitalToHospitalRequest(HospitalRequest hospitalRequest, Hospital hospital) {
       hospital.setHospitalName(hospitalRequest.getHospitalName());
       return hospital;
   }

   private HospitalResponse mapHospitalToHospitalResponse(Hospital hospital) {
       return HospitalResponse.builder()
               .hospitalId(hospital.getHospitalId())
               .hospitalName(hospital.getHospitalName())
               .build();
   }

//    @Override
//    public HospitalResponse addHospital(HospitalRequest hospitalRequest) {
//        Hospital hospital = mapHospitalToHospitalRequest(hospitalRequest,new Hospital());
//        hospitalRepository.save(hospital);
//
//        return mapHospitalToHospitalResponse(hospital);
//    }

    @Override
    public HospitalResponse findByHospitalId(int hospitalId) {
        Optional<Hospital> hospitalResult = hospitalRepository.findById(hospitalId);

          if(hospitalResult.isEmpty())
              throw new HospitalNotFoundException("hospital not found");

        Hospital hospital = hospitalResult.get();

        return mapHospitalToHospitalResponse(hospital);
    }

    @Override
    public HospitalResponse updateHospital(HospitalRequest hospitalRequest, int hospitalId) {
        Optional<Hospital> hospitalResult = hospitalRepository.findById(hospitalId);

        if(hospitalResult.isEmpty())
            throw new HospitalNotFoundException("Hospital Not Found by given id");


        Hospital existingHospital = hospitalResult.get();
        mapHospitalToHospitalRequest(hospitalRequest,existingHospital);
        hospitalRepository.save(existingHospital);

        return mapHospitalToHospitalResponse(existingHospital);
    }

    @Override
    public HospitalResponse addHospitalAdmin(HospitalRequest hospitalRequest, int adminId) {
        Optional<Admin> adminDetails = adminRepository.findById(adminId);

        if(adminDetails.isEmpty())
            throw new AdminNotFoundException("Admin Not found for the given Admin Id");

//        List<Admin> adminList = new ArrayList<>();
//        adminList.add(admin.get());

        Admin admin = adminDetails.get();


        Hospital hospital = mapHospitalToHospitalRequest(hospitalRequest,new Hospital());
        admin.setHospital(hospital);


        hospitalRepository.save(hospital);

        adminRepository.save(admin);

        return mapHospitalToHospitalResponse(hospital);

    }


//    @Override
//    public HospitalResponse addHospitalAdmin(HospitalRequest hospitalRequest,int userId) {
//
//        Optional<User> user = userRepository.findById(userId);
//        if(user.isEmpty())
//            throw new RuntimeException("User Not Found");
//
//        User existingUser = user.get();
////        if(User.builder().admin(existingUser)
////                .userRole(UserRole.ADMIN).admin(Admin.builder(AdminType.OWNER).build())
//
//User ownerAdmin = User.builder().userRole(UserRole.ADMIN).admin(Admin.builder().adminType(AdminType.OWNER).build()).build();
//
//
//
//
//    if(existingUser.getUserId() == ownerAdmin.getUserId())
//    {
//        Admin admin = Admin.builder().adminId()
//        hospital.setAdmin(hospital.getAdmin());
//
//
//
//        hospital.setAdmin(hospital.getAdmin());
//    }
//    }




}
