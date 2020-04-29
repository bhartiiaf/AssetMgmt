package com.unifiedweb360.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.user.Role;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.repo.master.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String serno) {
        User user = userRepository.findBySerno(serno);
        if (user == null) throw new UsernameNotFoundException(serno);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		 for (Role role : user.getRoles())
		 { 
			 grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		 }
		

        return new org.springframework.security.core.userdetails.User(user.getSerno(), user.getPassword(), grantedAuthorities);
    }
}