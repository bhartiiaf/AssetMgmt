package com.assetmgmt.modal.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.assetmgmt.modal.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="RANK_MASTER")
public class RankMaster {
	@Id
	private String rankCode;
	private String rankDispName,rankDescription, remarks, modifiedBy;
	private char cadre;
	private Date modifiedDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="rankMaster", cascade = CascadeType.MERGE)
	@JsonBackReference
	private List<User> users = new ArrayList<>();
	


}