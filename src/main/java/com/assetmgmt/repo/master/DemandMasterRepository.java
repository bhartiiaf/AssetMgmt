package com.assetmgmt.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assetmgmt.modal.master.DemandMaster;
import com.assetmgmt.modal.master.DemandNoMaster;


public interface DemandMasterRepository extends JpaRepository<DemandMaster,Integer> {
	
	
	@Query("from DemandMaster dm where dm.demandStatus='Draft' AND dm.demandedBy=?1")
	List<DemandMaster> findByDemandedBy(String demandedBy);
	@Query("from DemandMaster dm where dm.demandStatus='Finalised' AND dm.demandedBy=?1")
	List<DemandMaster> findByDemandedByAndDemandStatusFinalised(String demandedBy);
	
	
	List<DemandMaster> findByDemandNoMaster(DemandNoMaster demandNoMaster);
	
	@Query("from DemandMaster dm where dm.demandNoMaster.id=?1")
	List<Object[]> findAllDataById(Integer id);
	
	@Query("from DemandMaster dm where dm.demandNoMaster.id=?1 and dm.id=?2")
	public List<DemandMaster> getDemandMasterBydemandNoMaster(Integer demandNoMaster,Integer id);
	
	

	
	
	


}
