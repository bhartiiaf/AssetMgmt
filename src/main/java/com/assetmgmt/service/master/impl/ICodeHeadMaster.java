package com.assetmgmt.service.master.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assetmgmt.modal.master.CodeHeadMaster;
import com.assetmgmt.repo.master.CodeHeadMasterRepository;
import com.assetmgmt.service.master.CodeHeadMasterService;
@Service
public class ICodeHeadMaster implements CodeHeadMasterService {
	@Autowired
	CodeHeadMasterRepository codeHeadRepo;

	@Override
	public void save(CodeHeadMaster codeHeadMaster) {
		codeHeadMaster.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		codeHeadMaster.setCreatedOn(new Date());
		codeHeadRepo.save(codeHeadMaster);
	}

	@Override
	public List<CodeHeadMaster> findAll() {
		return codeHeadRepo.findAll();
	}

	@Override
	public List<CodeHeadMaster> findByCodeHeadDescription(String codeHeadDescription) {
		return codeHeadRepo.findByCodeHeadDescription(codeHeadDescription);
	}

	@Override
	public Optional<CodeHeadMaster> findById(Integer id) {
		return codeHeadRepo.findById(id);
	}

	@Override
	public List<Object[]> findAllById(int id) {
		return codeHeadRepo.findAllById(id);
	}

	@Override
	public CodeHeadMaster find(Integer id) {
		return codeHeadRepo.find(id);
	}

	

}
