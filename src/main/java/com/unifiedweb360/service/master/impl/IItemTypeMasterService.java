package com.unifiedweb360.service.master.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.repo.master.ItemTypeMasterRepository;
import com.unifiedweb360.service.master.ItemTypeMasterService;

@Service
public class IItemTypeMasterService implements ItemTypeMasterService {

	@Autowired
	ItemTypeMasterRepository itemTypeMasterRepo;
	@Override
	public void save(ItemTypeMaster itemTypeMaster) {
		itemTypeMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		itemTypeMaster.setCreatedOn(new Date());
		itemTypeMasterRepo.save(itemTypeMaster);

	}

	

	@Override
	public List<ItemTypeMaster> findByItemDescription(String itemDescription) {
		return itemTypeMasterRepo.findByItemDescription(itemDescription);
	}



	@Override
	public List<ItemTypeMaster> findAll() {
		return itemTypeMasterRepo.findAll();
	}



	@Override
	public ItemTypeMaster find(Integer id) {
		return itemTypeMasterRepo.find(id);
	}



	@Override
	public List<Object[]> findByCodeHeadMaster(Integer codeHeadMaster) {
		return itemTypeMasterRepo.findByCodeHeadMaster(codeHeadMaster);
	}



	@Override
	public void saveAll(List<ItemTypeMaster> t) {
		itemTypeMasterRepo.saveAll(t);
	}

	
}
