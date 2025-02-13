package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("From User where userName = :userName")
    public Optional<User> findByUserName(String userName);

    //MethodNamingConvention
    public Optional<User> findByEmail(String username);

    List<User> findByAvailableCityInAndBloodGroupIn(List<String> cities, List<BloodGroup> bloodGroup);
}
