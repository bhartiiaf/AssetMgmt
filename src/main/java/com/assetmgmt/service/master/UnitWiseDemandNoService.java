package com.assetmgmt.service.master;

import com.assetmgmt.modal.master.UnitMaster;

public interface UnitWiseDemandNoService {
		
		int unitWiseDemandNo(String unit, String finYear);

		void saveRecord(String pppFromTo, int i, UnitMaster unitMaster);
}
