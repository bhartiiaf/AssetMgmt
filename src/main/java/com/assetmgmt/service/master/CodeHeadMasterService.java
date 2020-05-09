package com.assetmgmt.service.master;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.assetmgmt.modal.master.CodeHeadMaster;

public interface CodeHeadMasterService {

	public void save(CodeHeadMaster codeHeadMaster);
	List<CodeHeadMaster> findAll();
	List<CodeHeadMaster> findByCodeHeadDescription(String codeHeadDescription);
	Optional<CodeHeadMaster> findById(Integer id);
	CodeHeadMaster find(Integer id);
	List<Object[]> findAllById(@RequestParam("id") int id);

	

}
