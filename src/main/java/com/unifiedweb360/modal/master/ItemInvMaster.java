package com.unifiedweb360.modal.master;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.unifiedweb360.modal.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="item_inv_master")
public class ItemInvMaster implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_INV_MASTER_SEQ")
    @SequenceGenerator(name="ITEM_INV_MASTER_SEQ", sequenceName="ITEM_INV_MASTER_SEQ",allocationSize=1)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="unit_id")
	private UnitMaster unitDetail;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userDetail;
	@ManyToOne
	@JoinColumn(name="item_type_id")
	private ItemTypeMaster itemType;
	private Date createdOn;
	private String createdBy;
}
