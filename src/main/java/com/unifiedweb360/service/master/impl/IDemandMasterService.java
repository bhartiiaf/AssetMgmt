package com.unifiedweb360.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.repo.master.DemandMasterRepository;
import com.unifiedweb360.service.master.DemandMasterService;

@Service
public class IDemandMasterService implements DemandMasterService {
@Autowired
DemandMasterRepository demandRepo;
	@Override
	public void save(DemandMaster demandMaster) {
		demandMaster.setDemandedOn(new Date());
		demandMaster.setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		demandRepo.save(demandMaster);
	}

	@Override
	public List<DemandMaster> findAll() {
		return demandRepo.findAll();
	}

}