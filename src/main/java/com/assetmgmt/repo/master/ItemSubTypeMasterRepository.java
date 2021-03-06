package com.assetmgmt.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.assetmgmt.modal.master.ItemSubTypeMaster;

public interface ItemSubTypeMasterRepository extends JpaRepository<ItemSubTypeMaster,Integer> {
	
	@Query("from ItemSubTypeMaster itsm where itsm.itemTypeId.id=?1")
	List<Object[]> findByItemTypeId(Integer itemTypeId);
	
	@Query("from ItemSubTypeMaster itsm where itsm.id=?1 ")
	ItemSubTypeMaster find(Integer id);
	List<ItemSubTypeMaster> findBySubTypeDesc(String subTypeDesc);
	
	List<ItemSubTypeMaster> findByOrderByItemTypeIdDesc();

	@Query("from ItemSubTypeMaster itsm where itsm.id=?1")
	List<Object[]> findAllDataById(int id);
	
	@Query("from ItemSubTypeMaster istm where istm.id=?1")
	public List<ItemSubTypeMaster> findById(int id);
}
