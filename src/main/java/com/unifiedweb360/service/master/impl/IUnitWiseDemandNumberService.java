package com.unifiedweb360.service.master.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.UnitMaster;
import com.unifiedweb360.modal.master.UnitWiseDemandNumber;
import com.unifiedweb360.repo.master.UnitWiseDemandNumberRepo;
import com.unifiedweb360.service.master.UnitWiseDemandNoService;


@Service
public class IUnitWiseDemandNumberService  implements UnitWiseDemandNoService{

	@Autowired
	UnitWiseDemandNumberRepo unitWiseDemandNumberRepo;
	@Override
	public int unitWiseDemandNo(String unit, String finYear) {
		
		return unitWiseDemandNumberRepo.getNextDemandNumer(unit, finYear);
	}
	
	@Override
	public void saveRecord(String pppFromTo, int i, UnitMaster unitMaster) {
		
		UnitWiseDemandNumber demandNumber  = new UnitWiseDemandNumber();
		demandNumber.setFinYear(pppFromTo);
		demandNumber.setUnitMaster(unitMaster);
		demandNumber.setDemandNumber(i);
		unitWiseDemandNumberRepo.save(demandNumber);
		
	}

}
