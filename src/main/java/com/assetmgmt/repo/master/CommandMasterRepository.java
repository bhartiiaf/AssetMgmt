package com.assetmgmt.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assetmgmt.modal.master.CommandMaster;

public interface CommandMasterRepository extends JpaRepository<CommandMaster, String> {

}
