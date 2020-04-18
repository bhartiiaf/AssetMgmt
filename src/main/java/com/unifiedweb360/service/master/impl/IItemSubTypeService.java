package com.unifiedweb360.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.ItemSubTypeMaster;
import com.unifiedweb360.repo.master.ItemSubTypeMasterRepository;
import com.unifiedweb360.service.master.ItemSubTypeService;

@Service
public class IItemSubTypeService implements ItemSubTypeService {
	@Autowired
	ItemSubTypeMasterRepository itemSubTypeRepo;

	@Override
	public void save(ItemSubTypeMaster itemSubTypeMaster) {
		itemSubTypeMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		itemSubTypeMaster.setCreatedOn(new Date());
		itemSubTypeRepo.save(itemSubTypeMaster);

	}

	@Override
	public List<Object[]> findByItemTypeId(Integer itemTypeId) {
		return itemSubTypeRepo.findByItemTypeId(itemTypeId);
	}

	@Override
	public ItemSubTypeMaster find(Integer id) {
		return itemSubTypeRepo.find(id);
	}

	@Override
	public List<ItemSubTypeMaster> findAll() {
		
		return itemSubTypeRepo.findAll();
	}

	@Override
	public List<ItemSubTypeMaster> findBySubTypeDesc(String subTypeDesc) {
		// TODO Auto-generated method stub
		return itemSubTypeRepo.findBySubTypeDesc(subTypeDesc);
	}

	@Override
	public List<ItemSubTypeMaster> findByOrder() {
		
		return itemSubTypeRepo.findByOrderByItemTypeIdDesc();
	}

	@Override
	public List<Object[]> findAllDataById(int id) {
		
		return itemSubTypeRepo.findAllDataById(id);
	}

	

}
