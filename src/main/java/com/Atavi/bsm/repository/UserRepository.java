package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>
{

}
