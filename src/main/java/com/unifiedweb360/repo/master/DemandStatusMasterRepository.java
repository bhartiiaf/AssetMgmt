package com.unifiedweb360.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unifiedweb360.modal.master.DemandStatusMaster;

public interface DemandStatusMasterRepository extends JpaRepository<DemandStatusMaster, Integer> {


	@Query("from DemandStatusMaster dsm where dsm.demandStatus='Finalised' ")
	public DemandStatusMaster findFinalisedDemand();
	
	@Query("from DemandStatusMaster dsm where dsm.id=?1")
	DemandStatusMaster find(Integer id);
	
	
	
}
