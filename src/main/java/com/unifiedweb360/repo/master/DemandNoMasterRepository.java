package com.unifiedweb360.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unifiedweb360.modal.master.DemandNoMaster;

public interface DemandNoMasterRepository extends JpaRepository<DemandNoMaster,Integer> {

	@Query("from DemandNoMaster dnm where dnm.id=?1 ")
	DemandNoMaster find(Integer id);
	
	List<DemandNoMaster> findByDemandNoGenerateddBy(String demandNoGenerateddBy);
	
	
}
