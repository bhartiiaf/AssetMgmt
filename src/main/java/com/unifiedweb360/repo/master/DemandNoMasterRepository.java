package com.unifiedweb360.repo.master;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.unifiedweb360.modal.master.DemandNoMaster;

public interface DemandNoMasterRepository extends JpaRepository<DemandNoMaster,Integer> {

	@Query("from DemandNoMaster dnm where dnm.id=?1 ")
	DemandNoMaster find(Integer id);
	
	List<DemandNoMaster> findByDemandNoGenerateddBy(String demandNoGenerateddBy);
	
	@Query("from DemandNoMaster dnm where dnm.unitId.unitCD=?1")
	List<DemandNoMaster> findByUnitCD(String unitCD);
	
	@Query("from DemandNoMaster dnm where dnm.id=?1")
	List<DemandNoMaster> findDataById(Integer id);
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query("update DemandNoMaster dnm set dnm.demandStatus=?1,dnm.demandLevel=?2 where dnm.id=?3 ")
	void updateData(String demandStatus,String demandLevel, int id);
	
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query("update DemandNoMaster dnm set dnm.demandLevel=?1 where dnm.id=?2 ")
	void updateDataForCMD(String demandLevel, int id);
	
	
	

}
