package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.UserService;
import com.Atavi.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
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
        User user = User.builder()
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .age(userRequest.getAge())
                .gender(userRequest.getGender())
                .availableCity(userRequest.getAvailableCity())
                .bloodGroup(userRequest.getBloodGroup())
                .lastDonatedAt(userRequest.getLastDonatedAt())
                .build();

                user = userRepository.save(user);

                return UserResponse.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .lastDonatedAt(user.getLastDonatedAt())
                        .gender(user.getGender())
                        .availableCity(user.getAvailableCity())
                        .verified(user.isVerified())
                        .bloodGroup(user.getBloodGroup())
                        .build();
    }

    @Override
    public UserResponse findByUserId(int userId) {
            Optional<User> userResult = userRepository.findById(userId);

          //  if(user.isEmpty())

            User user = userResult.get();

            return UserResponse.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .lastDonatedAt(user.getLastDonatedAt())
                    .gender(user.getGender())
                    .availableCity(user.getAvailableCity())
                    .verified(user.isVerified())
                    .bloodGroup(user.getBloodGroup())
                    .build();


    }


}
