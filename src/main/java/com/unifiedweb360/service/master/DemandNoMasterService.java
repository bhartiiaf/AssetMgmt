package com.unifiedweb360.service.master;

import java.util.List;
import java.util.Optional;

import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.modal.master.DemandStatusMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.modal.master.UnitMaster;
import com.unifiedweb360.service.ICommonService;

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
