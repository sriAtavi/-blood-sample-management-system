package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.UserResponse;

import com.Atavi.bsm.security.AuthUtil;
import com.Atavi.bsm.util.RestResponseBuilder;
import com.Atavi.bsm.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Atavi.bsm.service.UserService;
import com.Atavi.bsm.entity.User;

@RestController
@AllArgsConstructor //(Coz of this @Autowired (Field injection)is not used)
public class UserController
{
    private final UserService userService;
    private final RestResponseBuilder responseBuilder;
    private final AuthUtil authUtil;
   // private final AdminService adminService;
//    @PostMapping("/registerUser")
//    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user)
//
//    {
//
//        user = userService.addUser(user);
//
//        return ResponseStructure.createResponse(HttpStatus.CREATED.value(), "User Data Created", user);
//
//    }

    // For adding or registering User entity details directly which is the wrong way as shown below
//    @PostMapping("/registerUser")
//    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user)
//    {
//        user = userService.addUser(user);
//        return responseBuilder.success(HttpStatus.CREATED, "User Created", user);
//    }

    //Using DTO rather than entity directly which is to be done always and right approach
    @PostMapping("/registerUser")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest)
    {
        UserResponse  userResponse = userService.addUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }


//    @GetMapping("/users/{userId}")
//    public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(@PathVariable int userId)
//    {
//      UserResponse userResponse = userService.findByUserId(userId);
//        return responseBuilder.success(HttpStatus.FOUND, "User Found", userResponse);
//    }

    @GetMapping("/account")
    public ResponseEntity<ResponseStructure<UserResponse>> findByUserId()
    {
        UserResponse userResponse = userService.findUserAccount(); // This User account is found through AuthUtil User info
        return responseBuilder.success(HttpStatus.FOUND, "User Found", userResponse);
    }

//    @PutMapping("/users/{userId}")
//    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest,@PathVariable int userId)
//    {
//        UserResponse  userResponse = userService.updateUser(userRequest,userId);
//        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
//}


//We will be updating the user info based on the AuthUtil user found based on the Email not by the user ID
      @PutMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest)
   {
        UserResponse  userResponse = userService.updateUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

    @PatchMapping("/users/{userID}")
    public ResponseEntity<ResponseStructure<UserResponse>> verfiedUser(@RequestBody UserRequest userRequest ,@PathVariable int userID,@RequestParam boolean verified)
    {
        UserResponse  userResponse = userService.verfiedUser(userRequest,userID,verified);
        return responseBuilder.success(HttpStatus.CREATED, "User Verified", userResponse);
    }

    }

