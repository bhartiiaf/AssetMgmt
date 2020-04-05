package com.unifiedweb360.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.UnitMaster;
import com.unifiedweb360.repo.master.UnitMasterRepository;
import com.unifiedweb360.service.master.UnitMasterService;

@Service
public class IUnitMasterService implements UnitMasterService {
	@Autowired
	UnitMasterRepository unitRepo;

	@Override
	public void save(UnitMaster unitMaster) {
		unitRepo.save(unitMaster);
	}

}
