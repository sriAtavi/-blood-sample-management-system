package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.AdminType;
import com.Atavi.bsm.enums.UserRole;
import com.Atavi.bsm.exception.UserNotFoundException;
import com.Atavi.bsm.repository.AdminRepository;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.security.AuthUtil;
import com.Atavi.bsm.service.UserService;
import com.Atavi.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{


    private final PasswordEncoder passwordEncoder; // Constructor Injection
    private final AdminRepository adminRepository;
    private final AuthUtil authUtil;
    //Factory Method to Map the User Entity to UserRequest-DTO which will be using frequently and making this as Non-Static which would be safer
    // while Testing
    // The below method signature can be used only for adding new User
    //private  User mapUserRequestToUser(UserRequest userRequest) {

    //This method can be used for both Adding New User & Updating existing User also By taking User type parameter If we pass
    private  User mapUserRequestToUser(UserRequest userRequest,User user) {

        //This returns only new User
//        return User.builder()
//                .userName(userRequest.getUserName())
//                .password(userRequest.getPassword())
//                .email(userRequest.getEmail())
//                .phoneNumber(userRequest.getPhoneNumber())
//                .age(userRequest.getAge())
//                .gender(userRequest.getGender())
//                .availableCity(userRequest.getAvailableCity())
//                .bloodGroup(userRequest.getBloodGroup())
//                .lastDonatedAt(userRequest.getLastDonatedAt())
//                .build();

        //For below code , We cant use Builder, because it returns new Object everytime, here
        // Setting or creating new Object based on the value we receive in method parameter
                user.setUserName(userRequest.getUserName());
                user.setPassword(userRequest.getPassword());
                user.setEmail(userRequest.getEmail());
                user.setBloodGroup(userRequest.getBloodGroup());
                user.setAge(userRequest.getAge());
                user.setGender(userRequest.getGender());
                user.setAvailableCity(userRequest.getAvailableCity());
                user.setLastDonatedAt(userRequest.getLastDonatedAt());
                return user;
    }
//Factory Method to avoid Code Redundancy => To map 'User' to 'UserResponse DTO'
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .lastDonatedAt(user.getLastDonatedAt())
                .gender(user.getGender())
                .availableCity(user.getAvailableCity())
                .verified(user.isVerified())
                .bloodGroup(user.getBloodGroup())
                .userRole(user.getUserRole())
                .build();
    }


    //Autowired is not safe, If we use @AllArgsConstructor (Constructor Injection) Autowired can be ignored
    // Field Injection is not safe coz if there is no class also dependency is injected
    // Constructor injection ensures that class should be present
   private UserRepository userRepository;

// This is using User Entity Directly
//    @Override
//    public User addUser(User user)
//    {
//        return userRepository.save(user);
//    }


    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = mapUserRequestToUser(userRequest,new User());
                user.setUserRole(UserRole.USER);
                user.setPassword(passwordEncoder.encode(user.getPassword())); // Encoding Or Encrypting Password which is entered by user
                userRepository.save(user);

                return mapToUserResponse(user);
    }




//    @Override
//    public UserResponse findByUserId(int userId) {
//            Optional<User> userResult = userRepository.findById(userId);
//
//            if(userResult.isEmpty())
//                throw new UserNotFoundException("User Not Found");
//
//            User user = userResult.get();
//
//            return mapToUserResponse(user);
//    }



//    @Override
//    public UserResponse updateUser(UserRequest userRequest,int userId) {
//        Optional<User> userResult = userRepository.findById(userId);
//
//        if(userResult.isEmpty())
//            throw new UserNotFoundException("Failed To Update");
//
//
//        User existinguser = userResult.get();
//       // existinguser= mapUserRequestToUser(userRequest,existinguser);
//        mapUserRequestToUser(userRequest,existinguser);
//        userRepository.save(existinguser);
//
//        return mapToUserResponse(existinguser);
//
//} => This method

    @Override
    public UserResponse updateUser(UserRequest userRequest) {

      User existinguser = authUtil.getCurrentUserInfo(); //Exception is handled in AuthUtil
      this.mapUserRequestToUser(userRequest,existinguser);
        userRepository.save(existinguser);

        return mapToUserResponse(existinguser);

    }

    @Override
    public UserResponse findUserAccount() {
        User currentUserDetails = authUtil.getCurrentUserInfo();
           return mapToUserResponse(currentUserDetails);
    }

    @Override
    public UserResponse verfiedUser(UserRequest userRequest, int userID,boolean verified) {

        User existinguser = authUtil.getCurrentUserInfo();//Exception is handled in AuthUtil
        existinguser.setVerified(verified);
        this.mapUserRequestToUser(userRequest,existinguser);
        userRepository.save(existinguser);

        return mapToUserResponse(existinguser);
    }
}