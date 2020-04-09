package com.unifiedweb360.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unifiedweb360.modal.master.DemandMaster;


public interface DemandMasterRepository extends JpaRepository<DemandMaster,Integer> {
	
	
	List<DemandMaster> findByDemandNo(String demandNo);
	@Query("from DemandMaster dm where dm.demandStatus='Draft' AND dm.demandedBy=?1")
	List<DemandMaster> findByDemandedBy(String demandedBy);
	@Query("from DemandMaster dm where dm.demandStatus='Finalised' AND dm.demandedBy=?1")
	List<DemandMaster> findByDemandedByAndDemandStatusFinalised(String demandedBy);
	
	
	
	


}
