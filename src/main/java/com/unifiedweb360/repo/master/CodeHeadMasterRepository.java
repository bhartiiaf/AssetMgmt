package com.unifiedweb360.repo.master;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.unifiedweb360.modal.master.CodeHeadMaster;
public interface CodeHeadMasterRepository extends JpaRepository<CodeHeadMaster,Integer> {
	
	List<CodeHeadMaster> findByCodeHeadDescription(String codeHeadDescription);
	Optional<CodeHeadMaster> findById(Integer id);
	
	@Query("from CodeHeadMaster chm where chm.id=?1")
	List<Object[]> findAllById(@RequestParam("id") int id);
	
	
	@Query("from CodeHeadMaster chm where chm.id=?1")
	CodeHeadMaster find(Integer id);


}
