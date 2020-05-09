package com.assetmgmt.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.CommandMaster;
import com.assetmgmt.repo.master.CommandMasterRepository;
import com.assetmgmt.service.master.CommandMasterService;

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
	@Override
	public CommandMaster find(String commandCD) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CommandMaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(String commandCD) {
		// TODO Auto-generated method stub
		
	}

}
