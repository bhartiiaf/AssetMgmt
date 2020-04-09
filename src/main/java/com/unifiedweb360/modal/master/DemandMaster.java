package com.unifiedweb360.modal.master;

import java.io.Serializable;
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
@Table(name="demand_master")
public class DemandMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DEMAND_MASTER_SEQ")
    @SequenceGenerator(name="DEMAND_MASTER_SEQ", sequenceName="DEMAND_MASTER_SEQ",allocationSize=1)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="code_head_id")
	private CodeHeadMaster codeHeadId;
	@ManyToOne
	@JoinColumn(name="item_type_id")
	private ItemTypeMaster itemTypeId;
	@ManyToOne
	@JoinColumn(name="item_sub_type_id")
	private ItemSubTypeMaster itemSubTypeId;	
	private Integer itemQty;
	private String demandReason;
	private String demandAuth;
	private String demandedBy;
	private Date demandedOn;
	private String demandNo;
	private String demandStatus;
}
