package com.unifiedweb360.service.master;

import com.unifiedweb360.modal.master.UnitMaster;

public interface UnitWiseDemandNoService {
		
		int unitWiseDemandNo(String unit, String finYear);

		void saveRecord(String pppFromTo, int i, UnitMaster unitMaster);
}
