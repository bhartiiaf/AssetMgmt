package com.unifiedweb360.service.master;

import java.util.List;

import com.unifiedweb360.modal.master.DemandMaster;

public interface DemandMasterService {

	public void save(DemandMaster demandMaster);
	public List<DemandMaster> findAll();
	
	void saveAll(List<DemandMaster> t);
	
	public List<DemandMaster> findByDemandNo(String demandNo);
	
	List<DemandMaster> findByDemandedBy(String demandedBy);
	List<DemandMaster> findByDemandedByAndDemandStatusFinalised(String demandedBy);

}
