package com.unifiedweb360.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifiedweb360.modal.master.ItemInvMaster;
import com.unifiedweb360.repo.master.ItemInvMasterRepository;
import com.unifiedweb360.service.master.ItemInvMasterService;

@Service
public class IItemInvMasterService implements ItemInvMasterService {
	@Autowired
	ItemInvMasterRepository itemInvRepo;

	@Override
	public void save(ItemInvMaster itemInvMaster) {
		itemInvRepo.save(itemInvMaster);
	}

}
