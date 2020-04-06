package com.unifiedweb360.modal.master;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="unit_master")
public class UnitMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="UNIT_MASTER_SEQ")
    @SequenceGenerator(name="UNIT_MASTER_SEQ", sequenceName="UNIT_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String unitName;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="command_id")
	private CommandMaster commandId;
	private String createdBy;
	private Date createdOn;
}
