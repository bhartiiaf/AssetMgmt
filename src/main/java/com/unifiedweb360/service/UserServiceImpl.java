package com.unifiedweb360.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.usermodal.User;
import com.unifiedweb360.userrepo.RoleRepository;
import com.unifiedweb360.userrepo.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedOn(new Date());
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.setActive(true);
        userRepository.save(user);
    }

    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public List<User> findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}


	@Override
	public List<User> findUserByUserName(String username) {
		return userRepository.findUserByUsername(username);
	}


	
}