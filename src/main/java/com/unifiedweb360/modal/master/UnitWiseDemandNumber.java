package com.unifiedweb360.modal.master;

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
@Table(name="UNIT_WISE_DEMAND_NUMBER")
public class UnitWiseDemandNumber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="UNIT_WISE_DEMAND_NUMBER_SEQ")
    @SequenceGenerator(name="UNIT_WISE_DEMAND_NUMBER_SEQ", sequenceName="UNIT_WISE_DEMAND_NUMBER_SEQ",allocationSize=1)
	private int id;
	
	private String finYear;
	
	private int demandNumber;
	
	@ManyToOne
	@JoinColumn(name="UNIT")
	private UnitMaster unitMaster;
	

}
