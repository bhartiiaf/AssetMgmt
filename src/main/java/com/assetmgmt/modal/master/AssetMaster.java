/**
@Author SKJHA
*/
package com.assetmgmt.modal.master;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.assetmgmt.modal.user.User;

import lombok.Data;

@Data
@Entity
@Table(name="asset_master")
public class AssetMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ASSET_MASTER_SEQ")
    @SequenceGenerator(name="ASSET_MASTER_SEQ", sequenceName="ASSET_MASTER_SEQ",allocationSize=1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	private UnitMaster unitId;
	private String DteOrSectionInfo;
	private String FloorOrBlockInfo;
	@ManyToOne
	@JoinColumn(name="item_type_id")
	private ItemTypeMaster itemTypeId;
	private String itemMake;
	private String itemModel;
	private String itemSlNo;
	private Date dateOfInduction;
	private Date warrentyUpto;
	private String oSType;
	private String cpuType;
	private String rAMMake;
	private String rAMModel;
	private String rAMCapacity;
	private String hDDMake;
	private String hDDCapacity;
	private String hDDSlNo;
	private String monitorSize;
	private String monitorMake;
	private String printerType;
	private String printMode;
	private String printPaperType;
	private String printColorMonoInfo;
	private String lSDSize;
	private String upsCapacity;
	private String batteryCapacity;
	private String noOfBatteryBank;
	private String noOfBattery;
	private Date batteryInductionDate;
	private String projectorLuminence;
	private String scannerType;
	private String scannerPaperType;
	private String extHddCapacity;
	@ManyToOne
	@JoinColumn(name="custodian_serv_no")
	private User userId;
	private String createdBy;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedOn;

}


