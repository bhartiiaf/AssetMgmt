package com.assetmgmt.service.master;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.assetmgmt.modal.master.ItemSubTypeMaster;
import com.assetmgmt.modal.master.ItemTypeMaster;

public interface ItemSubTypeService {
	
	public void save(ItemSubTypeMaster itemSubTypeMaster);
	List<Object[]> findByItemTypeId(Integer itemTypeId);
	
	ItemSubTypeMaster find(Integer id);
	
	List<ItemSubTypeMaster> findAll();
	
    List<ItemSubTypeMaster> findBySubTypeDesc(String subTypeDesc);
    List<ItemSubTypeMaster> findByOrder();
    List<Object[]> findAllDataById(int id);
    public void deleteItemSubTypeById(int id);
    List<ItemSubTypeMaster> findAllDataByIdForUpdate(int id);


}
