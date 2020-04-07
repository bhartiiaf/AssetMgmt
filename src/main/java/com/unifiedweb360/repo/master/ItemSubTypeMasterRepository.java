package com.unifiedweb360.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.unifiedweb360.modal.master.ItemSubTypeMaster;

public interface ItemSubTypeMasterRepository extends JpaRepository<ItemSubTypeMaster,Integer> {
	
	@Query("from ItemSubTypeMaster itsm where itsm.itemTypeId.id=?1")
	List<Object[]> findByItemTypeId(Integer itemTypeId);

}
