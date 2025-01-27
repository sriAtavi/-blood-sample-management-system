package com.Atavi.bsm.security;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.exception.UserNotFoundException;
import com.Atavi.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// This class is written to get the Current User Logged info from the help of Static method -"getContext" of  'SecurityContextHolder' Class
// Which returns the Security Context Object type which will holds the User Logged info currently.
// getAuthentication().getName() => returns the Authenticated User from the Security Context

@Component
@AllArgsConstructor
public class AuthUtil {

    private final UserRepository userRepository;

    //here UserName will be EMAIL
    public String getCurrentUserName() { // This method returns the Authenticated Username which will be the Unique Email id provided while registering
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    //This method will return the Registered User Object based on the Current Username found in 'getCurrentUserName()'
    public User getCurrentUserInfo()
    {
//       Optional<User> currentUser = userRepository.findByEmail(this.getCurrentUserName());
//        if(currentUser.isEmpty())
//            throw new UserNotFoundException("Failed TO Authenticate User");
//
//        User userInfo = currentUser.get();
//        return userInfo;


        // The above method could be written in Java8 as below
        return userRepository.findByEmail(this.getCurrentUserName())
                .orElseThrow(()-> new UserNotFoundException("Failed TO Authenticate User"));
    }

}
