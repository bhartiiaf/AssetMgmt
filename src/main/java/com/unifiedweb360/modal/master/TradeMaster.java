package com.unifiedweb360.modal.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unifiedweb360.modal.user.User;

import lombok.Data;

@Data
@Entity
@Table(name="TRADE_MASTER")
public class TradeMaster {

	@Id
	private String tradeCode;
	private String tradeDispName, tradeFullName, modifiedBy, tradeType, remarks;
	private char trdaeGroup, techNtech, stream, category;
	private Date modifiedDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="tradeMaster", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<User> users = new ArrayList<>();
}