package com.unifiedweb360.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Override
	public CommandMaster find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CommandMaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
