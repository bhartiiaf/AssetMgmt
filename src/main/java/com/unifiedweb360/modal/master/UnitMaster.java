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

import lombok.Data;

@Data
@Entity
@Table(name="unit_master")
public class UnitMaster {

	
	@Id
	private String unitCD;
	
	private String unitDispName, locallity, area,opcontrol, admcontrol;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="command_id")
	private CommandMaster commandId;
	private String createdBy;
	private Date createdOn;
	
	@OneToMany
	private List<UnitWiseDemandNumber> demands = new ArrayList<>();
}
