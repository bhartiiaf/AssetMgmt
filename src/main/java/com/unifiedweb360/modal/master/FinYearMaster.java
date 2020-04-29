package com.unifiedweb360.modal.master;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="FINANCIAL_YEAR_MASTER")
public class FinYearMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="FIN_YEAR_SEQ")
    @SequenceGenerator(name="FIN_YEAR_SEQ", sequenceName="FIN_YEAR_SEQ",allocationSize=1)
	private Integer id;
	private String pppFromTo;
	private Integer isActive;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="finYearMaster",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<DemandNoMaster> demandNoMaster = new ArrayList<>();
	
	
	
}
