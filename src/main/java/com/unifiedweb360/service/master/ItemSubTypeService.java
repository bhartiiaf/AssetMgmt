package com.unifiedweb360.service.master;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.unifiedweb360.modal.master.ItemSubTypeMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;

public interface ItemSubTypeService {
	
	public void save(ItemSubTypeMaster itemSubTypeMaster);
	List<Object[]> findByItemTypeId(Integer itemTypeId);
	
	ItemSubTypeMaster find(Integer id);
	
	List<ItemSubTypeMaster> findAll();
	
    List<ItemSubTypeMaster> findBySubTypeDesc(String subTypeDesc);
    List<ItemSubTypeMaster> findByOrder();
    List<Object[]> findAllDataById(int id);


}
