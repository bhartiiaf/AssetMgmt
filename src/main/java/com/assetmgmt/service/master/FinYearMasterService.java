package com.assetmgmt.service.master;

import java.util.List;
import java.util.Optional;

import com.assetmgmt.modal.master.FinYearMaster;

public interface FinYearMasterService {
	
	Optional<FinYearMaster> findById(Integer id);
	List<FinYearMaster> findActiveFinYear();
	FinYearMaster find(Integer id);
	

}
