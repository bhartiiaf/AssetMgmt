package com.assetmgmt.service.master;

import java.util.List;
import java.util.Optional;

import com.assetmgmt.modal.master.DemandMaster;
import com.assetmgmt.modal.master.DemandNoMaster;
import com.assetmgmt.modal.master.DemandStatusMaster;
import com.assetmgmt.modal.master.ItemTypeMaster;
import com.assetmgmt.modal.master.UnitMaster;
import com.assetmgmt.service.ICommonService;

public interface DemandNoMasterService extends ICommonService<DemandNoMaster,Integer> {
	
List<DemandNoMaster> findByDemandNoGenerateddBy(String demandNoGenerateddBy);

List<DemandNoMaster> findByUnitCD(String unitCD);
DemandNoMaster find(Integer id);
void deleteById(Integer id);

List<DemandNoMaster> findDataById(Integer id);
void saveAll(List<DemandNoMaster> t);
void updateData(String demandStatus, String demandLevel, int id);
void updateDataForCMD(String demandLevel, int id);
void updateDataWithStatus(String demandStatus,String demandLevel,DemandStatusMaster did , int id);
void updateDraftDataWithStatus(DemandStatusMaster did , int id);
List<Object[]> totalDemand(String unitCD);




} 
