package com.assetmgmt.service.master.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.NotificationMaster;
import com.assetmgmt.repo.master.NotificaionMasterRepository;
import com.assetmgmt.service.master.NotificationMasterService;

@Service
public class INotificationMasterService implements NotificationMasterService {

	@Autowired
	NotificaionMasterRepository notificatioRepo;
	@Override
	public void save(NotificationMaster notificationMaster) {
		notificationMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		notificationMaster.setCreatedOn(new Date());
		notificatioRepo.save(notificationMaster);
	}
	
	

}
