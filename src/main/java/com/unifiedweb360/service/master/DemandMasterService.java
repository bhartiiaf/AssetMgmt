package com.unifiedweb360.service.master;

import java.util.List;
import java.util.Optional;

import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.modal.master.DemandNoMaster;

public interface DemandMasterService {

	public void save(DemandMaster demandMaster);
	public List<DemandMaster> findAll();
	
	void saveAll(List<DemandMaster> t);
	
	List<DemandMaster> findByDemandedBy(String demandedBy);
	List<DemandMaster> findByDemandedByAndDemandStatusFinalised(String demandedBy);
	Optional<DemandMaster> findById(Integer id);
	List<Object[]> findAllDataById(Integer id);
	
	public List<DemandMaster> getDemandMasterBydemandNoMaster(Integer demandNoMaster,Integer id);
	
	public void deleteById(Integer id);

}
