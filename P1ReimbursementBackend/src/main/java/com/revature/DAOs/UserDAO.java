package com.revature.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDAO extends JpaRepository<User,Integer>{
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username,String password);
}