package com.assetmgmt.service.master.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.ItemTypeMaster;
import com.assetmgmt.repo.master.ItemTypeMasterRepository;
import com.assetmgmt.service.master.ItemTypeMasterService;

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



	@Override
	public void deleteItemTypeById(int id) {
		itemTypeMasterRepo.deleteById(id);
	}

	
}
