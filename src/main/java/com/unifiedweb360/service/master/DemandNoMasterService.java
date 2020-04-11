package com.unifiedweb360.service.master;

import java.util.List;

import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.service.ICommonService;

public interface DemandNoMasterService extends ICommonService<DemandNoMaster,Integer> {
	
List<DemandNoMaster> findByDemandNoGenerateddBy(String demandNoGenerateddBy);
}
