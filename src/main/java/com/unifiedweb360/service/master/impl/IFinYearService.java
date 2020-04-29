package com.unifiedweb360.service.master.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.FinYearMaster;
import com.unifiedweb360.repo.master.FinYearMasterRepository;
import com.unifiedweb360.service.master.FinYearMasterService;

@Service
public class IFinYearService implements FinYearMasterService{

	@Autowired
	FinYearMasterRepository finYearMasterRepository;
	
	
	@Override
	public Optional<FinYearMaster> findById(Integer id) {
		return finYearMasterRepository.findById(id);
	}
	@Override
	public List<FinYearMaster> findActiveFinYear() {
		
		return finYearMasterRepository.findActiveFinYear();
	}
	@Override
	public FinYearMaster find(Integer id) {
		
		return finYearMasterRepository.find(id);
	}
	
		
		
	
	

}
