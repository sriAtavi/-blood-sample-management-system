package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.service.UserService;
import com.Atavi.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    //Autowired is not safe, If we use @AllArgsConstructor (Constructor Injection) Autowired can be ignored
    // Field Injection is not safe coz if there is no class also dependency is injected
    // Constructor injection ensures that class should be present
   private UserRepository userRepository;

    @Override
    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserId(int userId) {
        return userRepository.findById(userId);
    }


}
