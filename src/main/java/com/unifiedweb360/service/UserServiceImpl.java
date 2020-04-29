package com.unifiedweb360.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.repo.master.RoleRepository;
import com.unifiedweb360.repo.master.UserRepository;



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
        user.setIsValid(1);
        userRepository.save(user);
    }

    
    @Override
    public User findBySerno(String serno) {
        return userRepository.findBySerno(serno);
    }

	@Override
	public List<User> findBySernoAndPassword(String serno, String password) {
		return userRepository.findBySernoAndPassword(serno, password);
	}


	@Override
	public List<User> findUserBySerno(String serno) {
		return userRepository.findUserBySerno(serno);
	}


	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}


	
}