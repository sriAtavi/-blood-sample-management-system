package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.Admin;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.AdminType;
import com.Atavi.bsm.enums.UserRole;
import com.Atavi.bsm.exception.UserNotFoundException;
import com.Atavi.bsm.repository.AdminRepository;
import com.Atavi.bsm.repository.UserRepository;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private  User mapUserRequestToUser(UserRequest userRequest,User user) {
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

    @Override
    public UserResponse promoteUser(UserRequest userRequest, int userId) {
        Optional<User> userResult = userRepository.findById(userId);

        if(userResult.isEmpty())
            throw new UserNotFoundException("Failed to find user");

        User existUser = userResult.get();
        //existUser.setUserRole(UserRole.ADMIN);
        existUser.setUserRole(UserRole.GUEST_ADMIN); // We are using Roles from User Entity only

        mapUserRequestToUser(userRequest,existUser);

        userRepository.save(existUser);

        Admin admin = Admin.builder().user(existUser).build();

        adminRepository.save(admin);
        return mapToUserResponse(existUser);
    }

    @Override
    public UserResponse addAdminUsers(UserRequest userRequest) {

        User user = mapUserRequestToUser(userRequest,new User());
        //user.setUserRole(UserRole.ADMIN);
        user.setUserRole(UserRole.OWNER_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        Admin admin = Admin.builder()
                .user(user)
                .build();

        adminRepository.save(admin);
        return mapToUserResponse(user);
    }
    }



