package com.unifiedweb360.service.master;

import java.util.List;

import com.unifiedweb360.modal.master.ItemTypeMaster;

public interface ItemTypeMasterService {
	void saveAll(List<ItemTypeMaster> t);

	void save(ItemTypeMaster itemTypeMaster);
	List<ItemTypeMaster> findAll();
	List<ItemTypeMaster> findByItemDescription(String itemDescription);
	
	ItemTypeMaster find(Integer id);
	List<Object[]> findByCodeHeadMaster(Integer codeHeadMaster);
    public void deleteItemTypeById(int id);



}
