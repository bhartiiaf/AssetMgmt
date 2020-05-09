package com.assetmgmt.modal.master;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="UNIT_SCALE_MASTER")
public class UnitScaleMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="UNIT_SCALE_MASTER_SEQ")
    @SequenceGenerator(name="UNIT_SCALE_MASTER_SEQ", sequenceName="UNIT_SCALE_MASTER_SEQ",allocationSize=1)
	private Integer unitScaleID;
	
	private Integer quantity, isValid;
	
	private String createdBy, modifiedBy;
	private Date createdOn, modifiedOn, fromDate, toDate;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<UnitMaster> unitMasterId;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<ItemTypeMaster> itemTypeMasterID;
}