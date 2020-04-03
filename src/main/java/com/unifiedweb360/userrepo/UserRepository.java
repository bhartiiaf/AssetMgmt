package com.unifiedweb360.userrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifiedweb360.usermodal.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    List<User> findByUsernameAndPassword(String username, String password);
}