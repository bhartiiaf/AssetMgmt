package com.unifiedweb360.service.master;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.unifiedweb360.modal.master.ItemSubTypeMaster;

public interface ItemSubTypeService {
	
	public void save(ItemSubTypeMaster itemSubTypeMaster);
	List<Object[]> findByItemTypeId(Integer itemTypeId);


}
