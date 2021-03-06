package com.assetmgmt.modal.master;

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
@Table(name="demand_no_master")
public class DemandNoMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DEMAND_NO_MASTER_SEQ")
    @SequenceGenerator(name="DEMAND_NO_MASTER_SEQ", sequenceName="DEMAND_NO_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String demandMasterNo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="demandNoMaster",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<DemandMaster> demandMaster = new ArrayList<>();
	private String demandNoGenerateddBy;
	private Date demandNoGeneratedOn;
	@ManyToOne
	@JoinColumn(name="unit_id")
	private UnitMaster unitId;
	private String demandStatus;
	@ManyToOne
	@JoinColumn(name="fin_year")
	private FinYearMaster finYearMaster;
	private String demandLevel;
	@ManyToOne
	@JoinColumn(name="approval_status")
	private DemandStatusMaster demandStatusId;
}
