package com.assetmgmt.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.DemandStatusMaster;
import com.assetmgmt.repo.master.DemandStatusMasterRepository;
import com.assetmgmt.service.master.DemandStatusMasterService;

@Service
public class IDemandStatusMasterService implements DemandStatusMasterService {

	@Autowired
	DemandStatusMasterRepository demandStatusRepo;
	@Override
	public DemandStatusMaster find(Integer id) {
		return demandStatusRepo.find(id);
	}

	@Override
	public List<DemandStatusMaster> findAll() {
		return demandStatusRepo.findAll();
	}

	@Override
	public void save(DemandStatusMaster t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
