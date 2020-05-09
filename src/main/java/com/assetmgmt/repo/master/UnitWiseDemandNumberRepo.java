package com.assetmgmt.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assetmgmt.modal.master.UnitWiseDemandNumber;


public interface UnitWiseDemandNumberRepo extends JpaRepository<UnitWiseDemandNumber, Integer> {
	
	@Query
	("select 0+COALESCE(max(uwdn.demandNumber), 0)+1 from UnitWiseDemandNumber uwdn where uwdn.unitMaster.unitDispName=?1 and uwdn.finYear=?2")
    int getNextDemandNumer(String unit, String finYear);

}
