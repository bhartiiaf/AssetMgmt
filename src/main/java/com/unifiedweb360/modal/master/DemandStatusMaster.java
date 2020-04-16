package com.unifiedweb360.modal.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="demand_status_master")
public class DemandStatusMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DEMAND_STATUS_MASTER_SEQ")
    @SequenceGenerator(name="DEMAND_STATUS_MASTER_SEQ", sequenceName="DEMAND_STATUS_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String demandStatus;
	private String createdBy;
	private String createdOn;
	
}
