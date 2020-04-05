package com.unifiedweb360.service.master;

import java.util.List;
import java.util.Optional;

import com.unifiedweb360.modal.master.ItemTypeMaster;

public interface ItemTypeMasterService {
	
	void save(ItemTypeMaster itemTypeMaster);
	List<ItemTypeMaster> findAllItemData();
	Optional<ItemTypeMaster> findById(Integer id);

}