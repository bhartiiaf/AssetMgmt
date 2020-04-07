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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;



/**
 * @author SKJHA
 *
 */

@Data
@Entity
@Table(name = "PersonalUser")
public class User {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_MASTER_SEQ")
    @SequenceGenerator(name="USER_MASTER_SEQ", sequenceName="USER_MASTER_SEQ",allocationSize=1)
 	
    private Long id;
    private String username;
    private String memberName;
    private String email;
    private String mobile;
    private boolean isActive;
    private String password;
    private Date createdOn;
    private String createdBy;
    @Transient
    private String passwordConfirm;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns= @JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;
    private String authLevel;

    }
