package com.unifiedweb360.repo.master;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.modal.master.DemandStatusMaster;
import com.unifiedweb360.modal.master.UnitMaster;

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
	@Query("update DemandNoMaster dnm set dnm.demandStatus=?1,dnm.demandLevel=?2,dnm.demandStatusId=?3 where dnm.id=?4 ")
	void updateDataWithStatus(String demandStatus,String demandLevel,DemandStatusMaster did , int id);
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query("update DemandNoMaster dnm set dnm.demandStatusId=?1 where dnm.id=?2 ")
	void updateDraftDataWithStatus(DemandStatusMaster did , int id);
	
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query("update DemandNoMaster dnm set dnm.demandLevel=?1 where dnm.id=?2 ")
	void updateDataForCMD(String demandLevel, int id);
	
	
	@Query("select count(*), dnm.unitId.unitDispName,dnm.demandStatusId.demandStatus from DemandNoMaster dnm where dnm.unitId.unitCD=?1 GROUP BY dnm.unitId.unitDispName,dnm.demandStatusId.demandStatus")
	List<Object[]> totalDemand(String unitCD);

	
	
	

}
