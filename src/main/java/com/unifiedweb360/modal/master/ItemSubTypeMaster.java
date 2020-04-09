package com.unifiedweb360.modal.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="item_sub_type_master")
public class ItemSubTypeMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_SUB_TYPE_MASTER_SEQ")
    @SequenceGenerator(name="ITEM_SUB_TYPE_MASTER_SEQ", sequenceName="ITEM_SUB_TYPE_MASTER_SEQ",allocationSize=1)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="item_type_id")
	private ItemTypeMaster itemTypeId;
	private String subTypeDesc;
	private Double subItemPrice;
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="itemSubTypeId")
	@JsonBackReference
	private List<DemandMaster> demandMasterForItem = new ArrayList<>();
	private Date createdOn;
	private String createdBy;

}
