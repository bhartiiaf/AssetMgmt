package com.unifiedweb360.service.master.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		itemTypeMasterRepo.save(itemTypeMaster);

	}

	@Override
	public List<ItemTypeMaster> findAllItemData() {
	return itemTypeMasterRepo.findAll();
	}

	@Override
	public Optional<ItemTypeMaster> findById(Integer id) {
		return itemTypeMasterRepo.findById(id);
	}
}