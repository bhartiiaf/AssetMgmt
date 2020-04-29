package com.unifiedweb360.service;

import java.util.List;
import java.util.Optional;

import com.unifiedweb360.modal.user.User;


public interface UserService {
    void save(User user);

    User findBySerno(String serno);
    List<User> findBySernoAndPassword(String serno, String password);
    
    List<User> findUserBySerno(String serno);
    
    List<User> findAllUser();
    
}