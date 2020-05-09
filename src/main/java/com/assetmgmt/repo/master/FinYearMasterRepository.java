package com.assetmgmt.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assetmgmt.modal.master.FinYearMaster;

public interface FinYearMasterRepository extends JpaRepository<FinYearMaster, Integer>{
	
	@Query("from FinYearMaster fym where fym.isActive=1")
	public List<FinYearMaster> findActiveFinYear();

	
	@Query("from FinYearMaster fym where fym.id=?1")
	FinYearMaster find(Integer id);
}
