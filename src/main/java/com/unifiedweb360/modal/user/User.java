package com.unifiedweb360.modal.user;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.unifiedweb360.modal.master.RankMaster;
import com.unifiedweb360.modal.master.TradeMaster;
import com.unifiedweb360.modal.master.UnitMaster;

import lombok.Data;



/**
 * @author SKJHA
 *
 */

@Data
@Entity
@Table(name = "USER_MASTER")
public class User {
    @Id
    private String serno;
    private String fullName;
    private Integer isValid;
    private String password;
    private Date createdOn;
    private String createdBy;
    @Transient
    private String passwordConfirm;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns= @JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;
    private String authLevel;
    @ManyToOne
    @JoinColumn(name="unit_id")
    private UnitMaster unitId;
    
    @ManyToOne
	@JoinColumn(name="RANK_CODE")
	private RankMaster rankMaster;
    
    @ManyToOne
	@JoinColumn(name="TRADE_CODE")
	private TradeMaster tradeMaster;

    }
