package com.assetmgmt.service.usermgmt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.user.Role;
import com.assetmgmt.repo.master.RoleRepository;
import com.assetmgmt.service.usermgmt.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

}
