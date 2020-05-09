package com.assetmgmt.service.master.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.ItemInvMaster;
import com.assetmgmt.repo.master.ItemInvMasterRepository;
import com.assetmgmt.service.master.ItemInvMasterService;

@Service
public class IItemInvMasterService implements ItemInvMasterService {
	@Autowired
	ItemInvMasterRepository itemInvRepo;

	@Override
	public void save(ItemInvMaster itemInvMaster) {
		itemInvMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		itemInvMaster.setCreatedOn(new Date());
		itemInvRepo.save(itemInvMaster);
	}

}
