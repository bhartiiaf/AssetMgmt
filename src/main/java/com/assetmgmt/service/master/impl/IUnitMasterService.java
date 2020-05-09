package com.assetmgmt.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.UnitMaster;
import com.assetmgmt.repo.master.UnitMasterRepository;
import com.assetmgmt.service.master.UnitMasterService;

@Service
public class IUnitMasterService implements UnitMasterService {
	@Autowired
	private UnitMasterRepository unitRepo;

	@Override
	public void save(UnitMaster unitMaster) {
		unitMaster.setCreatedOn(new Date());
		unitRepo.save(unitMaster);
	}

	@Override
	public UnitMaster find(String id) {
		return null;
	}

	@Override
	public List<UnitMaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//test
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
