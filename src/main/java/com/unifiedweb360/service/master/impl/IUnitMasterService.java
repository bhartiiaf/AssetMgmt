package com.unifiedweb360.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.UnitMaster;
import com.unifiedweb360.repo.master.UnitMasterRepository;
import com.unifiedweb360.service.master.UnitMasterService;

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
	public UnitMaster find(Integer id) {
		return null;
	}

	@Override
	public List<UnitMaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//test
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
