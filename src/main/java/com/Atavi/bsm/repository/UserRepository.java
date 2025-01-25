package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("From User where userName = :userName")
    public Optional<User> findByUserName(String userName);

    //MethodNamingConvention
    public Optional<User> findByEmail(String username);
}
