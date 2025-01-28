package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.AdminService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final RestResponseBuilder responseBuilder;


    //RBAC => Role Based Access Control
    // @PreAuthorize => permits us to provide Authorization to whichever endpoints has the authorities at Method Level instead of giving authorities
    // to endpoints separately in SecurityFilterChain classes
    //@PostAuthorize
    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
   @PutMapping("/users/{userId}")
   public ResponseEntity<ResponseStructure<UserResponse>> promoteUser(@RequestBody UserRequest userRequest, @PathVariable int userId)
   {
      UserResponse  userResponse = adminService.promoteUser(userRequest,userId);
      return responseBuilder.success(HttpStatus.CREATED, "User Promoted", userResponse);

   }

   @PostMapping("/adminUsers")
   public ResponseEntity<ResponseStructure<UserResponse>> addAdminUsers(@RequestBody UserRequest userRequest)
   {
      UserResponse  userResponse = adminService.addAdminUsers(userRequest);
      return responseBuilder.success(HttpStatus.CREATED, "Admin Created", userResponse);
   }
}
