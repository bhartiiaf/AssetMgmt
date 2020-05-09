package com.assetmgmt.modal.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author SKJHA
 *
 */
@Data
@Entity
@Table(name = "ROLE_MASTER")
public class Role {
    @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ROLE_MASTER_SEQ")
    @SequenceGenerator(name="ROLE_MASTER_SEQ", sequenceName="ROLE_MASTER_SEQ",allocationSize=1)
 	  
    private Long id;
    private String name;
    private  String createdBy, modifiedBy;
    private Date createdOn, modifiedDate;
    

}
