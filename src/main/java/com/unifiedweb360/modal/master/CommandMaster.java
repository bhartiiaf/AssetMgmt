package com.unifiedweb360.modal.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="command_master")
public class CommandMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="COMMAND_MASTER_SEQ")
    @SequenceGenerator(name="COMMAND_MASTER_SEQ", sequenceName="COMMAND_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String commandName;
	private Date createdOn;
	private String createdBy;
}
