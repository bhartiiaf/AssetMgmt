package com.unifiedweb360.service.master.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.modal.master.FinYearMaster;
import com.unifiedweb360.modal.master.UnitMaster;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.repo.master.DemandMasterRepository;
import com.unifiedweb360.service.UserService;
import com.unifiedweb360.service.master.CodeHeadMasterService;
import com.unifiedweb360.service.master.DemandMasterService;
import com.unifiedweb360.service.master.DemandNoMasterService;
import com.unifiedweb360.service.master.DemandStatusMasterService;
import com.unifiedweb360.service.master.FinYearMasterService;
import com.unifiedweb360.service.master.ItemSubTypeService;
import com.unifiedweb360.service.master.ItemTypeMasterService;
import com.unifiedweb360.service.master.UnitWiseDemandNoService;

@Service
public class IDemandMasterService implements DemandMasterService {
@Autowired
DemandMasterRepository demandRepo;
@Autowired
DemandMasterService demandService;
@Autowired
CodeHeadMasterService codeHeadService;
@Autowired
ItemTypeMasterService itemTypeService;
@Autowired
ItemSubTypeService itemSubTypeService;
@Autowired
UserService userService;
@Autowired
DemandNoMasterService demandNoMasterService;
@Autowired
DemandStatusMasterService demandStatusService;
@Autowired
FinYearMasterService finYearMasterService;

@Autowired
UnitWiseDemandNoService unitWiseDemandNoService;



	@Override
	public void save(DemandMaster demandMaster) {
		demandMaster.setDemandedOn(new Date());
		demandMaster.setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		demandRepo.save(demandMaster);
	}

	@Override
	public List<DemandMaster> findAll() {
		return demandRepo.findAll();
	}

	@Override
	public void saveAll(List<DemandMaster> t) {
		demandRepo.saveAll(t);
	}	

	@Override
	public List<DemandMaster> findByDemandedBy(String demandedBy) {
		return demandRepo.findByDemandedBy(demandedBy);
	}

	@Override
	public List<DemandMaster> findByDemandedByAndDemandStatusFinalised(String demandedBy) {
		return demandRepo.findByDemandedByAndDemandStatusFinalised(demandedBy);
	}

	@Override
	public Optional<DemandMaster> findById(Integer id) {
		return demandRepo.findById(id);
	}

	
	

	@Override
	public List<Object[]> findAllDataById(Integer id) {
		return demandRepo.findAllDataById(id);
	}

	@Override
	public List<DemandMaster> getDemandMasterBydemandNoMaster(Integer demandNoMaster, Integer id) {
		return demandRepo.getDemandMasterBydemandNoMaster(demandNoMaster, id);
	}

	@Override
	public void deleteById(Integer id) {
		demandRepo.deleteById(id);
	}

	

	//Demand Update & Save

	public void updateAndSave(HttpServletRequest request,RedirectAttributes redirectAttribute) {
		int dataSize = request.getParameterValues("codeHeadId").length;
		String dmnofornew = request.getParameter("demandNoMaster");
		String action= request.getParameter("submitvalue");
		List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
		if(u.iterator().next().getAuthLevel().equals("UNIT"))
		{
			if(action.equals("Draft"))
			{
			demandNoMasterService.updateData(action, "UNIT",Integer.parseInt(dmnofornew));
			}
			else if(action.equals("Finalised"))
			{
			demandNoMasterService.updateData(action,"CMD", Integer.parseInt(dmnofornew));

			}
		}
		else if(u.iterator().next().getAuthLevel().equals("CMD"))
		{
			if(action.equals("Draft"))
			{
			demandNoMasterService.updateData(action, "CMD",Integer.parseInt(dmnofornew));
			}
			else if(action.equals("Finalised"))
			{
			demandNoMasterService.updateData(action,"AHQ", Integer.parseInt(dmnofornew));

			}
		}
		else if(u.iterator().next().getAuthLevel().equals("AHQ"))
		{
			if(action.equals("Draft"))
			{
			demandNoMasterService.updateData(action, "AHQ",Integer.parseInt(dmnofornew));
			}
			else if(action.equals("Finalised"))
			{
			demandNoMasterService.updateData(action,"Finalised", Integer.parseInt(dmnofornew));

			}
		}
		
		List<DemandMaster> dm = new ArrayList<>();
		for (int i = 0; i < dataSize; i++) {
		if(request.getParameterValues("id")[i]=="")
		{
				DemandMaster dmm = new DemandMaster();
				String codeHeadId = request.getParameterValues("codeHeadId")[i];
				System.out.println(codeHeadId);
				String itemTypeId = request.getParameterValues("itemTypeId")[i];
				String itemSubTypeId = request.getParameterValues("itemSubTypeId")[i];
				Integer itemQty = Integer.parseInt(request.getParameterValues("itemQty")[i]);
				String demandReason = request.getParameterValues("demandReason")[i];
				String demandAuth = request.getParameterValues("demandAuth")[i];
				dmm.setCodeHeadId(codeHeadService.find(Integer.parseInt(codeHeadId)));
				dmm.setItemTypeId(itemTypeService.find(Integer.parseInt(itemTypeId)));
				dmm.setItemSubTypeId(itemSubTypeService.find(Integer.parseInt(itemSubTypeId)));
				dmm.setItemQty(itemQty);
				dmm.setDemandAuth(demandAuth);
				dmm.setDemandReason(demandReason);
				dmm.setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				dmm.setDemandedOn(new Date());
				dmm.setDemandNoMaster(demandNoMasterService.find(Integer.parseInt(dmnofornew)));
				dmm.setDemandStatus(action);
				dm.add(dmm);

		}
		else
		{
				Integer id = Integer.parseInt(request.getParameterValues("id")[i]);
				Integer dnm = Integer.parseInt(request.getParameterValues("demandNoMaster")[i]);
				List<DemandMaster> dmm = demandService.getDemandMasterBydemandNoMaster(dnm,id);
				String codeHeadId = request.getParameterValues("codeHeadId")[i];
				System.out.println(codeHeadId);
				String itemTypeId = request.getParameterValues("itemTypeId")[i];
				String itemSubTypeId = request.getParameterValues("itemSubTypeId")[i];
				Integer itemQty = Integer.parseInt(request.getParameterValues("itemQty")[i]);
				String demandReason = request.getParameterValues("demandReason")[i];
				String demandAuth = request.getParameterValues("demandAuth")[i];
				dmm.iterator().next().setCodeHeadId(codeHeadService.find(Integer.parseInt(codeHeadId)));
				dmm.iterator().next().setItemTypeId(itemTypeService.find(Integer.parseInt(itemTypeId)));
				dmm.iterator().next().setItemSubTypeId(itemSubTypeService.find(Integer.parseInt(itemSubTypeId)));
				dmm.iterator().next().setItemQty(itemQty);
				dmm.iterator().next().setDemandAuth(demandAuth);
				dmm.iterator().next().setDemandReason(demandReason);
				dmm.iterator().next().setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				dmm.iterator().next().setDemandedOn(new Date());
				dmm.iterator().next().setDemandNoMaster(demandNoMasterService.find(dnm));
				dmm.iterator().next().setId(id);
				dmm.iterator().next().setDemandStatus(action);
				dm.addAll(dmm);
		}
		demandService.saveAll(dm);
		}
		redirectAttribute.addFlashAttribute("successedited","Demand No : " +dm.iterator().next().getDemandNoMaster().getDemandMasterNo()+ " is saved successfully");
		
	}
	
	
	
	//Update Cmd Data
	
	@Override
	public void updateAndSaveForCMD(HttpServletRequest request, RedirectAttributes redirectAttribute) {
			int dataSize = request.getParameterValues("codeHeadId").length;
			String dmnofornew = request.getParameter("demandNoMaster");
			String action= request.getParameter("submitvalue");
			List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
			 if(u.iterator().next().getAuthLevel().equals("CMD"))
			{
				if(action.equals("Draft"))
				{
				demandNoMasterService.updateDataForCMD("CMD",Integer.parseInt(dmnofornew));
				}
				else if(action.equals("Finalised"))
				{
				demandNoMasterService.updateDataForCMD("AHQ", Integer.parseInt(dmnofornew));

				}
			}
			
			List<DemandMaster> dm = new ArrayList<>();
			for (int i = 0; i < dataSize; i++) {
			
					Integer id = Integer.parseInt(request.getParameterValues("id")[i]);
					Integer dnm = Integer.parseInt(request.getParameterValues("demandNoMaster")[i]);
					List<DemandMaster> dmm = demandService.getDemandMasterBydemandNoMaster(dnm,id);
					String codeHeadId = request.getParameterValues("codeHeadId")[i];
					System.out.println(codeHeadId);
					String itemTypeId = request.getParameterValues("itemTypeId")[i];
					String itemSubTypeId = request.getParameterValues("itemSubTypeId")[i];
					Integer itemQty = Integer.parseInt(request.getParameterValues("itemQty")[i]);
					String demandReason = request.getParameterValues("demandReason")[i];
					String demandAuth = request.getParameterValues("demandAuth")[i];
					Integer ItemQtyApproved = Integer.parseInt(request.getParameterValues("cmdApprovedQty")[i]);
					String cmdRemark = request.getParameterValues("cmdRemarks")[i];
					dmm.iterator().next().setCodeHeadId(codeHeadService.find(Integer.parseInt(codeHeadId)));
					dmm.iterator().next().setItemTypeId(itemTypeService.find(Integer.parseInt(itemTypeId)));
					dmm.iterator().next().setItemSubTypeId(itemSubTypeService.find(Integer.parseInt(itemSubTypeId)));
					dmm.iterator().next().setItemQty(itemQty);
					dmm.iterator().next().setDemandAuth(demandAuth);
					dmm.iterator().next().setDemandReason(demandReason);
					dmm.iterator().next().setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
					dmm.iterator().next().setDemandedOn(new Date());
					dmm.iterator().next().setDemandNoMaster(demandNoMasterService.find(dnm));
					dmm.iterator().next().setCmdApprovedQty(ItemQtyApproved);
					dmm.iterator().next().setCmdRemarks(cmdRemark);
					dmm.iterator().next().setId(id);
					dmm.iterator().next().setDemandStatus("Finalised");
					dm.addAll(dmm);
			
			demandService.saveAll(dm);
			}
			if(action.equals("Draft"))
			{
			redirectAttribute.addFlashAttribute("successedited","Demand No : " +dm.iterator().next().getDemandNoMaster().getDemandMasterNo()+ " is saved successfully");
			}
			else if(action.equals("Finalised"))
			{
			redirectAttribute.addFlashAttribute("successedited","Demand No : " +dm.iterator().next().getDemandNoMaster().getDemandMasterNo()+ "submitted for the perusal of AHQ");
			}
		}
		
		
	// For AHQ Level
	
	@Override
	public void updateAndSaveForAHQ(HttpServletRequest request, RedirectAttributes redirectAttribute) {
			int dataSize = request.getParameterValues("codeHeadId").length;
			String dmnofornew = request.getParameter("demandNoMaster");
			String action= request.getParameter("submitvalue");
			List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
			 if(u.iterator().next().getAuthLevel().equals("AHQ"))
			{
				if(action.equals("Draft"))
				{
				demandNoMasterService.updateDataForCMD("AHQ",Integer.parseInt(dmnofornew));
				}
				else if(action.equals("Finalised"))
				{
				demandNoMasterService.updateDataForCMD("AHQ", Integer.parseInt(dmnofornew));

				}
			}
			
			List<DemandMaster> dm = new ArrayList<>();
			for (int i = 0; i < dataSize; i++) {
			
					Integer id = Integer.parseInt(request.getParameterValues("id")[i]);
					Integer dnm = Integer.parseInt(request.getParameterValues("demandNoMaster")[i]);
					List<DemandMaster> dmm = demandService.getDemandMasterBydemandNoMaster(dnm,id);
					String codeHeadId = request.getParameterValues("codeHeadId")[i];
					System.out.println(codeHeadId);
					String itemTypeId = request.getParameterValues("itemTypeId")[i];
					String itemSubTypeId = request.getParameterValues("itemSubTypeId")[i];
					Integer itemQty = Integer.parseInt(request.getParameterValues("itemQty")[i]);
					String demandReason = request.getParameterValues("demandReason")[i];
					String demandAuth = request.getParameterValues("demandAuth")[i];
					Integer ItemQtyApprovedbycmd = Integer.parseInt(request.getParameterValues("cmdApprovedQty")[i]);
					String cmdRemark = request.getParameterValues("cmdRemarks")[i];
					Integer ItemQtyApprovedbydit = Integer.parseInt(request.getParameterValues("ditApprovedQty")[i]);
					String ditRemark = request.getParameterValues("ditRemarks")[i];
					dmm.iterator().next().setCodeHeadId(codeHeadService.find(Integer.parseInt(codeHeadId)));
					dmm.iterator().next().setItemTypeId(itemTypeService.find(Integer.parseInt(itemTypeId)));
					dmm.iterator().next().setItemSubTypeId(itemSubTypeService.find(Integer.parseInt(itemSubTypeId)));
					dmm.iterator().next().setItemQty(itemQty);
					dmm.iterator().next().setDemandAuth(demandAuth);
					dmm.iterator().next().setDemandReason(demandReason);
					dmm.iterator().next().setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
					dmm.iterator().next().setDemandedOn(new Date());
					dmm.iterator().next().setDemandNoMaster(demandNoMasterService.find(dnm));
					dmm.iterator().next().setCmdApprovedQty(ItemQtyApprovedbycmd);
					dmm.iterator().next().setCmdRemarks(cmdRemark);
					dmm.iterator().next().setId(id);
					dmm.iterator().next().setDitApprovedQty(ItemQtyApprovedbydit);
					dmm.iterator().next().setDitRemarks(ditRemark);
					dmm.iterator().next().setDemandStatus("Finalised");
					dm.addAll(dmm);
			
			demandService.saveAll(dm);
			}
			redirectAttribute.addFlashAttribute("successedited","Demand No : " +dm.iterator().next().getDemandNoMaster().getDemandMasterNo()+ " is saved successfully");
			
		}
	
	
	
	
	
	
	
	
	
	//save method

	@Override
	public void saveDemandMethod(HttpServletRequest request, RedirectAttributes redirectAttribute,DemandMaster demandMaster,DemandNoMaster demandNoMaster) {

		List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
		int fy = Integer.parseInt(request.getParameter("financeYearId"));
		if(u.iterator().next().getAuthLevel().equals("UNIT"))
		{
			 if
			  (request.getParameter("saveandsubmit") != null) 
			  {
				 demandNoMaster.setDemandLevel("CMD");
			  }
			 else if(request.getParameter("saveasdraft") != null) 
			  {
				 demandNoMaster.setDemandLevel("UNIT");
			  }
		}
		else if(u.iterator().next().getAuthLevel().equals("CMD"))
		{
			if
			  (request.getParameter("saveandsubmit") != null) 
			  {
				demandNoMaster.setDemandLevel("AHQ");
			  }
			else if(request.getParameter("saveasdraft") != null) 
			  {
				demandNoMaster.setDemandLevel("CMD");
			  }

		}
		else if(u.iterator().next().getAuthLevel().equals("AHQ"))
		{
			demandNoMaster.setDemandLevel("AHQ");
		}
		Optional<FinYearMaster> fym = finYearMasterService.findById(fy);
		int demandNoLatest = unitWiseDemandNoService.unitWiseDemandNo(u.iterator().next().getUnitId().getUnitDispName(), fym.get().getPppFromTo());
		unitWiseDemandNoService.saveRecord( fym.get().getPppFromTo(), demandNoLatest,u.iterator().next().getUnitId());
		String demandnoalloted =  u.iterator().next().getUnitId().getUnitDispName()+"/"+fym.get().getPppFromTo()+"/"+demandNoLatest;
		  demandNoMaster.setDemandMasterNo(demandnoalloted);
		  demandNoMaster.setFinYearMaster(finYearMasterService.find(fy));
		
		  demandNoMaster.setUnitId(u.iterator().next().getUnitId()); 
		  if
		  (request.getParameter("saveandsubmit") != null) 
		  {
		  demandNoMaster.setDemandStatus("Finalised");
		  } else if(request.getParameter("saveasdraft") != null) 
		  {
		  demandNoMaster.setDemandStatus("Draft");
		  }
		
		  demandNoMasterService.save(demandNoMaster);
			
		int dataSize = request.getParameterValues("codeHeadId").length;
		List<DemandMaster> dm = new ArrayList<>();
		for (int i = 0; i < dataSize; i++) {
			DemandMaster dmm = new DemandMaster();
			String codeHeadId = request.getParameterValues("codeHeadId")[i];
			System.out.println(codeHeadId);
			String itemTypeId = request.getParameterValues("itemTypeId")[i];
			String itemSubTypeId = request.getParameterValues("itemSubTypeId")[i];
			Integer itemQty = Integer.parseInt(request.getParameterValues("itemQty")[i]);
			String demandReason = request.getParameterValues("demandReason")[i];
			String demandAuth = request.getParameterValues("demandAuth")[i];
			dmm.setCodeHeadId(codeHeadService.find(Integer.parseInt(codeHeadId)));
			dmm.setItemTypeId(itemTypeService.find(Integer.parseInt(itemTypeId)));
			dmm.setItemSubTypeId(itemSubTypeService.find(Integer.parseInt(itemSubTypeId)));
			dmm.setItemQty(itemQty);
			dmm.setDemandAuth(demandAuth);
			dmm.setDemandReason(demandReason);
			dmm.setDemandedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			dmm.setDemandedOn(new Date());
			dmm.setDemandNoMaster(demandNoMaster);
			if (request.getParameter("saveandsubmit") != null) {
				dmm.setDemandStatus("Finalised");
			} else if (request.getParameter("saveasdraft") != null) {
				dmm.setDemandStatus("Draft");
				
				redirectAttribute.addFlashAttribute("draft", "Demand in Draft Mode");
			}
			dm.add(dmm);
			
		}

		demandService.saveAll(dm);
		
		redirectAttribute.addFlashAttribute("success","Demand Raised with Demand No: " +dm.iterator().
								next().getDemandNoMaster().getDemandMasterNo()+ " | Demand Status is:  "+ dm.iterator().next().getDemandStatus());
		
	}

	

	
	

	

	
	

	

}
