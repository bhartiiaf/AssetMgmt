package com.assetmgmt.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.ItemSubTypeMaster;
import com.assetmgmt.repo.master.ItemSubTypeMasterRepository;
import com.assetmgmt.service.master.ItemSubTypeService;

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

	

	@Override
	public List<ItemSubTypeMaster> findAllDataByIdForUpdate(int id) {
		return itemSubTypeRepo.findById(id);
	}

	@Override
	public void deleteItemSubTypeById(int id) {
		itemSubTypeRepo.deleteById(id);
	}

	

}
