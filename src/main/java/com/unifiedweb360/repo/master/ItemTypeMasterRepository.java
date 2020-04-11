package com.unifiedweb360.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unifiedweb360.modal.master.ItemTypeMaster;

public interface ItemTypeMasterRepository extends JpaRepository<ItemTypeMaster, Integer> {
	
	List<ItemTypeMaster> findByItemDescription(String itemDescription);

	@Query("from ItemTypeMaster itm where itm.id = ?1 ")
	ItemTypeMaster find(Integer id);
	
	@Query("from ItemTypeMaster itm where itm.codeHeadMaster.id=?1")
	List<Object[]> findByCodeHeadMaster(Integer codeHeadMaster);

}
