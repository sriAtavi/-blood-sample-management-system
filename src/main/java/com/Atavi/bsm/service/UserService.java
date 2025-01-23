package com.Atavi.bsm.service;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;

import java.util.Optional;


public interface  UserService
{
    //public User addUser(User  user);  this is for directly using User Entity which is not the right approach

    public UserResponse addUser(UserRequest userRequest); // This is using DTOs instead of using User Entity

    public UserResponse findByUserId(int userId);

   public UserResponse updateUser(UserRequest userRequest,int userId);
}
