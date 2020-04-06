package com.unifiedweb360.service.master.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.CommandMaster;
import com.unifiedweb360.repo.master.CommandMasterRepository;
import com.unifiedweb360.service.master.CommandMasterService;

@Service
public class ICommandMasterService implements CommandMasterService {

	@Autowired
	CommandMasterRepository commandRepo;
	@Override
	public void save(CommandMaster commandMaster) {
		commandMaster.setCreatedOn(new Date());
		commandMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		commandRepo.save(commandMaster);
	}

}
