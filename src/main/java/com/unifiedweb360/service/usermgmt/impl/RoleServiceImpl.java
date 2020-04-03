package com.unifiedweb360.service.usermgmt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.service.usermgmt.RoleService;
import com.unifiedweb360.usermodal.Role;
import com.unifiedweb360.userrepo.RoleRepository;



@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

}
