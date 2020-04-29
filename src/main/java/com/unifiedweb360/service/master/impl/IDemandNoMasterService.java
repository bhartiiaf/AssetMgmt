package com.unifiedweb360.service.master.impl;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.repo.master.DemandNoMasterRepository;
import com.unifiedweb360.service.master.DemandNoMasterService;

@Service
public class IDemandNoMasterService implements DemandNoMasterService {
	@Autowired
	private DemandNoMasterRepository demandNoMasterRepo;

	@Override
	public void save(DemandNoMaster demandNoMaster) {
		demandNoMaster.setDemandNoGenerateddBy(SecurityContextHolder.getContext().getAuthentication().getName());
		demandNoMaster.setDemandNoGeneratedOn(new Date());
		demandNoMasterRepo.save(demandNoMaster);
		
	}

	@Override
	public DemandNoMaster find(Integer id) {
		return demandNoMasterRepo.find(id);
	}

	@Override
	public List<DemandNoMaster> findAll() {
		return demandNoMasterRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DemandNoMaster> findByDemandNoGenerateddBy(String demandNoGenerateddBy) {
		return demandNoMasterRepo.findByDemandNoGenerateddBy(demandNoGenerateddBy);
	}

	@Override
	public List<DemandNoMaster> findByUnitCD(String unitCD) {
		return demandNoMasterRepo.findByUnitCD(unitCD);
	}

	@Override
	public void deleteById(Integer id) {
		demandNoMasterRepo.deleteById(id);
	}

	@Override
	public List<DemandNoMaster> findDataById(Integer id) {
		return demandNoMasterRepo.findDataById(id);
	}

	@Override
	public void saveAll(List<DemandNoMaster> t) {
		demandNoMasterRepo.saveAll(t);
	}

	@Override
	public void updateData(String demandStatus,String demandLevel, int id) {
		demandNoMasterRepo.updateData(demandStatus,demandLevel, id);
	}

	@Override
	public void updateDataForCMD(String demandLevel, int id) {
		demandNoMasterRepo.updateDataForCMD(demandLevel, id);
	}

	

	

	

	
	

}
