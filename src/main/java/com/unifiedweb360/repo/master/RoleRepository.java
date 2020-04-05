package com.unifiedweb360.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifiedweb360.modal.user.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}