package com.Atavi.bsm.service;
import com.Atavi.bsm.entity.User;

import java.util.Optional;


public interface  UserService
{
    public User addUser(User user);

    public Optional<User> findByUserId(int userId);
}
