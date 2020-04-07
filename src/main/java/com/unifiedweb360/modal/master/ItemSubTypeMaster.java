package com.unifiedweb360.modal.master;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name="item_sub_type_master")
public class ItemSubTypeMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_SUB_TYPE_MASTER_SEQ")
    @SequenceGenerator(name="ITEM_SUB_TYPE_MASTER_SEQ", sequenceName="ITEM_SUB_TYPE_MASTER_SEQ",allocationSize=1)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="item_type_id")
	private ItemTypeMaster itemTypeId;
	private String subTypeDesc;
	private Date createdOn;
	private String createdBy;

}
