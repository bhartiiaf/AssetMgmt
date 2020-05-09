package com.assetmgmt.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assetmgmt.modal.user.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}