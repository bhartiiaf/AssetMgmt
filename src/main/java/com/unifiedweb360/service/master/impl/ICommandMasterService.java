package com.unifiedweb360.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
		commandRepo.save(commandMaster);
	}

}
