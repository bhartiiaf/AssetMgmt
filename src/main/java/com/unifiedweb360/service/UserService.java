package com.unifiedweb360.service;

import java.util.List;


import com.unifiedweb360.usermodal.User;


public interface UserService {
    void save(User user);

    User findByUsername(String username);
    List<User> findByUsernameAndPassword(String username, String password);
    
    List<User> findUserByUserName(String username);
    
}