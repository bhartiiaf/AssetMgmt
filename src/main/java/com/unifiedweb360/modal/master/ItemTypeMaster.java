package com.unifiedweb360.modal.master;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="item_type_master")
public class ItemTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_TYPE_MASTER_SEQ")
    @SequenceGenerator(name="ITEM_TYPE_MASTER_SEQ", sequenceName="ITEM_TYPE_MASTER_SEQ",allocationSize=1)
	private Integer id;
	private String itemDescription;
	private Date createdOn;
	private String createdBy;
}
