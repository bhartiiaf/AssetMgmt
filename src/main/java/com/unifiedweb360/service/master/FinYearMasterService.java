package com.unifiedweb360.service.master;

import java.util.List;
import java.util.Optional;

import com.unifiedweb360.modal.master.FinYearMaster;

public interface FinYearMasterService {
	
	Optional<FinYearMaster> findById(Integer id);
	List<FinYearMaster> findActiveFinYear();
	FinYearMaster find(Integer id);
	

}
