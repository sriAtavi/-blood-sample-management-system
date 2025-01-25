package com.Atavi.bsm.security;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.exception.UserNotFoundException;
import com.Atavi.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userResult =  userRepository.findByEmail("bhoomi@gmail");
        if(userResult.isEmpty())
       throw new UsernameNotFoundException("Failed TO Authenticate User"); // 'UsernameNotFoundException' is Provided from SpringSecurity
            System.out.println();

        User userData = userResult.get();

        //The below 'User' is from "org.springframework.security.core.userdetails" where this class stores the Actual Application User Data in In-Memory
        // In-Memory Cache=> Where our Application is running-> it could be in local machine or Distributed Server
        return org.springframework.security.core.userdetails.User.builder()
                .username(userData.getEmail())
                .password(userData.getPassword())
                .build();
    }
}
