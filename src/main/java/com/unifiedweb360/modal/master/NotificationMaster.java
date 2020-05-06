package com.unifiedweb360.modal.master;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="notification_master")
public class NotificationMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="NOTIFY_MASTER_SEQ")
    @SequenceGenerator(name="NOTIFY_MASTER_SEQ", sequenceName="NOTIFY_MASTER_SEQ",allocationSize=1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="demand_no_id")
	DemandNoMaster demandNoId;

	@Column(name="NOTIFICATION_DUE_ON")
	private Date notificationDue;
	
	private int notificationStatus;
	
	private String createdBy;
	private Date createdOn;
	

	
}
