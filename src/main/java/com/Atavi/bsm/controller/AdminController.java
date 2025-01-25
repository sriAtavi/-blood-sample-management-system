package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.AdminService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final RestResponseBuilder responseBuilder;

   @PutMapping("/userPromotion/{userId}")
   public ResponseEntity<ResponseStructure<UserResponse>> promoteUser(@RequestBody UserRequest userRequest, @PathVariable int userId)
   {
      UserResponse  userResponse = adminService.promoteUser(userRequest,userId);
      return responseBuilder.success(HttpStatus.CREATED, "User Promoted", userResponse);

   }

   @PostMapping("/addAdminUsers")
   public ResponseEntity<ResponseStructure<UserResponse>> addAdminUsers(@RequestBody UserRequest userRequest)
   {
      UserResponse  userResponse = adminService.addAdminUsers(userRequest);
      return responseBuilder.success(HttpStatus.CREATED, "Admin Created", userResponse);
   }
}
