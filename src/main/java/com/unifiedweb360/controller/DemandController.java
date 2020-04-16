package com.unifiedweb360.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unifiedweb360.modal.master.CodeHeadMaster;
import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.modal.master.DemandNoMaster;
import com.unifiedweb360.modal.master.DemandStatusMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.service.UserService;
import com.unifiedweb360.service.master.CodeHeadMasterService;
import com.unifiedweb360.service.master.DemandMasterService;
import com.unifiedweb360.service.master.DemandNoMasterService;
import com.unifiedweb360.service.master.DemandStatusMasterService;
import com.unifiedweb360.service.master.ItemSubTypeService;
import com.unifiedweb360.service.master.ItemTypeMasterService;

@Controller
public class DemandController {
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
	
	

	
	@GetMapping("/demand")
	public ModelAndView getDemandPage(HttpServletRequest request,DemandNoMaster demandNoMaster) {

		ModelAndView mv = new ModelAndView("demandmaster");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findByUsername(uname);
		List<DemandNoMaster> demandByUnitId = demandNoMasterService.findByUnitId(u1.getUnitId().getId()).stream().filter(q -> q.getDemandMaster().iterator().next().getDemandStatus().equals("Finalised")).collect(Collectors.toList());
		List<DemandNoMaster> demandByUnitIdDraft = demandNoMasterService.findByUnitId(u1.getUnitId().getId()).stream().filter(q -> q.getDemandMaster().iterator().next().getDemandStatus().equals("Draft")).collect(Collectors.toList());
		List<DemandMaster> demandMasterInDraftMode = demandService.findAll().stream().filter(x -> x.getDemandStatus().equals("Draft")).collect(Collectors.toList());

		List<DemandMaster> dMaster = demandService.findByDemandedBy(u1.getUsername());
		List<DemandMaster> dMasterFin = demandService.findByDemandedByAndDemandStatusFinalised(u1.getUsername());
		mv.addObject("urole", u1.getRoles().iterator().next().getName());
		List<User> u = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CodeHeadMaster> chmaster = codeHeadService.findAll();
		List<CodeHeadMaster> onlyahq = chmaster.stream().filter(c -> c.getCodeHeadType().equals("CENTRAL"))
				.collect(Collectors.toList());
		List<CodeHeadMaster> onlycmdstn = chmaster.stream().filter(c -> c.getCodeHeadType().equals("LOCAL"))
				.collect(Collectors.toList());
		
		List<DemandNoMaster> demandforcmd = demandNoMasterService.findByUnitId(u1.getUnitId().getCommandId().getId());
		demandforcmd.stream().filter(x -> x.getDemandMaster().iterator().next().getDemandStatus().equals("Finalised")).collect(Collectors.toList());
		
		for (User us : u) {
			if (us.getAuthLevel().equals("AHQ")) {
				mv.addObject("onlyahq", onlyahq);
				

			} else if (us.getAuthLevel().equals("COMMAND")) {
				mv.addObject("onlyahq", onlycmdstn);
				mv.addObject("forCmd",demandMasterInDraftMode);
			} else if (us.getAuthLevel().equals("UNIT")) {
				mv.addObject("onlyahq", onlycmdstn);

			}

		}
		List<DemandMaster> dm = demandService.findAll();
		List<ItemTypeMaster> itm = itemTypeService.findAll();
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		mv.addObject("demandforcmd",demandforcmd);
		mv.addObject("fetchDemandNO",demandByUnitId);
		mv.addObject("demandmode",demandByUnitIdDraft);
		mv.addObject("demanddraft", dMaster);
		mv.addObject("findemand", dMasterFin);
		mv.addObject("demand", dm);
		mv.addObject("itemtype", itm);
		mv.addObject("codehead", chm);
		return mv;

	}
	
	@PostMapping("/submitdemand")
	public ModelAndView submitDemand(HttpServletRequest request, DemandMaster demandMaster,
			DemandNoMaster demandNoMaster, RedirectAttributes redirectAttribute) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmm");
		String strDate = formatter.format(date);
		String demandnoalloted = SecurityContextHolder.getContext().getAuthentication().getName() + strDate;
		List<User> u = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		
			demandNoMaster.setDemandMasterNo(demandnoalloted);
			demandNoMaster.setUnitId(u.iterator().next().getUnitId());
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
		
		return new ModelAndView("redirect:/demand");
	}

	
	@PostMapping("/changedemandstatus")
	public ModelAndView changeDemandStatus(HttpServletRequest request,DemandMaster demandMaster,
			 RedirectAttributes redirectAttribute)
	{
		int dataSize = request.getParameterValues("codeHeadId").length;
		List<DemandMaster> dm = new ArrayList<>();
		String action= request.getParameter("submitvalue");
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
			demandService.saveAll(dm);
		}
		redirectAttribute.addFlashAttribute("successedited","Demand No : " +dm.iterator().
				next().getDemandNoMaster().getDemandMasterNo()+ " is saved successfully");
		return new ModelAndView("redirect:/demand");
	}
	
	
	
	@GetMapping("/fetchcodeheaddata")
	public @ResponseBody CodeHeadMaster fetchCodeHeadDataByDesc(CodeHeadMaster codeHeadMaster,
			@RequestParam("codeHead") String codeHead) {
		CodeHeadMaster am = new CodeHeadMaster();
		codeHeadService.findById(Integer.parseInt(codeHead));
		return am;
	}

	@GetMapping("/codeheaddata")
	public @ResponseBody List<Object[]> fetchCodeHeadData(HttpServletRequest request,
			@RequestParam("codeHead") String codeHead) {
		return codeHeadService.findAllById(Integer.parseInt(codeHead));
	}

	@GetMapping("/fetchsubitem")
	public @ResponseBody List<Object[]> fetchSubItem(HttpServletRequest request,
			@RequestParam("itemTypeId") Integer itemTypeId)
	{
		return itemSubTypeService.findByItemTypeId(itemTypeId);
	}
	
	@GetMapping("/fetchitemtypebycodehead")
	public @ResponseBody List<Object[]> fetchItemTybeByCodeHead(HttpServletRequest request,
			@RequestParam("codeHead") Integer codeHead)
	{
		return itemTypeService.findByCodeHeadMaster(codeHead);
	}

	@GetMapping(value = "editdemanddataindraftmode/{id}")
	public ModelAndView fetchDemandDetail(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("editdemandindraft");
		Optional<DemandMaster> dmaster = demandService.findById(id);
		mv.addObject("draftdemand", dmaster.get());
		return mv;
	}

	@GetMapping(value = "fetchdemanddata/{id}")
	public @ResponseBody List<Object[]> fetchDemandData(@PathVariable("id") int id) {
		return demandService.findAllDataById(id);
	}
	
	
	@GetMapping(value = "deletequery/{id}")
	public String deleteQuery(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		demandService.deleteById(id);
		redirectAttribute.addFlashAttribute("successdelete","Item ID "+ id +" is deleted");
		return "redirect:/demand";
	}
	
	@GetMapping(value="deletedemandnoindraft/{id}")
	public String deleteDemandNoInDraft(@PathVariable("id") int id, RedirectAttributes redirect) {
		demandNoMasterService.deleteById(id);
		redirect.addFlashAttribute("successdeleteofdraftdemand","Demand No is Deleted");

		return "redirect:/demand";
	}
	
	
}
