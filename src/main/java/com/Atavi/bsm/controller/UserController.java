package com.Atavi.bsm.controller;

import com.Atavi.bsm.util.RestResponseBuilder;
import com.Atavi.bsm.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Atavi.bsm.service.UserService;
import com.Atavi.bsm.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor //(Coz of this @Autowired (Field injection)is not used)
public class UserController
{
    private final UserService userService;
    private final RestResponseBuilder responseBuilder;

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

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user)
    {
        user = userService.addUser(user);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", user);
    }

    public ResponseEntity<ResponseStructure<User>> findByUserId(@PathVariable int userId)
    {
      User user = userService.findByUserId(userId);
    }
}