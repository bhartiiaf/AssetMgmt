package com.unifiedweb360.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unifiedweb360.modal.master.ItemTypeMaster;

public interface ItemTypeMasterRepository extends JpaRepository<ItemTypeMaster, Integer> {
	
	List<ItemTypeMaster> findByItemDescription(String itemDescription);


}
