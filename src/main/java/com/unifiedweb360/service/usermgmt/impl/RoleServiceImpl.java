package com.unifiedweb360.service.usermgmt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.user.Role;
import com.unifiedweb360.repo.master.RoleRepository;
import com.unifiedweb360.service.usermgmt.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

}
