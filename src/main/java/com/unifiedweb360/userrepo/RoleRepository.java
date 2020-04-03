package com.unifiedweb360.userrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifiedweb360.usermodal.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}