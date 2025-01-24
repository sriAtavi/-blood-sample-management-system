package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;

public interface AdminService {

    UserResponse promoteUser(UserRequest userRequest, int userId);

    UserResponse addAdminUsers(UserRequest userRequest);
}
