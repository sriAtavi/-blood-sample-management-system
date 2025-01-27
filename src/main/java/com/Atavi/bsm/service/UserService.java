package com.Atavi.bsm.service;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;

import java.util.Optional;


public interface  UserService
{
    //public User addUser(User  user);  this is for directly using User Entity which is not the right approach

    public UserResponse addUser(UserRequest userRequest); // This is using DTOs instead of using User Entity

  //  public UserResponse findByUserId(int userId); =>

  // public UserResponse updateUser(UserRequest userRequest,int userId);
// Above two methods were earlier used way of finding and updating by user Id which was wrong, coz he might update other User details also.


    public UserResponse updateUser(UserRequest userRequest);

    UserResponse findUserAccount();

    // 'updateUser()' & 'findUserAccount()' now uses the Authorised user or Currently logged-in user who is able to find and update his details only

    UserResponse verfiedUser(UserRequest userRequest, int userID,boolean verified);
    //verfiedUser() => used to patch map the Verified user by Admin not by any user by himself


}
