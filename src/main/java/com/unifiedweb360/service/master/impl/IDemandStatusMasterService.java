package com.unifiedweb360.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.DemandStatusMaster;
import com.unifiedweb360.repo.master.DemandStatusMasterRepository;
import com.unifiedweb360.service.master.DemandStatusMasterService;

@Service
public class IDemandStatusMasterService implements DemandStatusMasterService {

	@Autowired
	DemandStatusMasterRepository demandStatusRepo;
	@Override
	public DemandStatusMaster find(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
