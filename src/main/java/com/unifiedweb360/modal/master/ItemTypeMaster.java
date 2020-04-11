package com.unifiedweb360.modal.master;

import java.io.Serializable;
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
@Table(name="item_type_master")
public class ItemTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_TYPE_MASTER_SEQ")
    @SequenceGenerator(name="ITEM_TYPE_MASTER_SEQ", sequenceName="ITEM_TYPE_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String itemDescription;
	private Date createdOn;
	private String createdBy;
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="itemTypeId" , cascade = CascadeType.MERGE)
	@JsonBackReference
	private List<DemandMaster> demandMaster = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="code_head_master_id")
	private CodeHeadMaster codeHeadMaster;
}
