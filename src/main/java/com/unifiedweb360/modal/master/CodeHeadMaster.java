package com.unifiedweb360.modal.master;

import java.io.Serializable;
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
@Table(name="code_head_master")
public class CodeHeadMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CODE_HEAD_MASTER_SEQ")
    @SequenceGenerator(name="CODE_HEAD_MASTER_SEQ", sequenceName="CODE_HEAD_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String codeHead;
	private String codeHeadDescription;
	private String codeHeadType;
	private Date createdOn;
	private String createdBy;
}
