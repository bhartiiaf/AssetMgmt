package com.assetmgmt.service;

import java.util.List;
import java.util.Optional;

import com.assetmgmt.modal.user.User;


public interface UserService {
    void save(User user);

    User findBySerno(String serno);
    List<User> findBySernoAndPassword(String serno, String password);
    
    List<User> findUserBySerno(String serno);
    
    List<User> findAllUser();
    
}