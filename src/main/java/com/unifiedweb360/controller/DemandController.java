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
import com.unifiedweb360.modal.master.FinYearMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.service.UserService;
import com.unifiedweb360.service.master.CodeHeadMasterService;
import com.unifiedweb360.service.master.DemandMasterService;
import com.unifiedweb360.service.master.DemandNoMasterService;
import com.unifiedweb360.service.master.DemandStatusMasterService;
import com.unifiedweb360.service.master.FinYearMasterService;
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
	@Autowired
	FinYearMasterService finYearService;

	@GetMapping("/demand")
	public ModelAndView getDemandPage(HttpServletRequest request, DemandNoMaster demandNoMaster) {

		
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		
		ModelAndView mv = new ModelAndView("demandmaster");
		List<DemandNoMaster> demandByUnitId = demandNoMasterService.findByUnitCD(u1.getUnitId().getUnitCD()).stream()
				.filter(q -> q.getDemandMaster().iterator().next().getDemandStatus().equals("Finalised"))
				.collect(Collectors.toList());
		List<DemandNoMaster> demandByUnitIdDraft = demandNoMasterService.findByUnitCD(u1.getUnitId().getUnitCD()).stream()
				.filter(q -> q.getDemandMaster().iterator().next().getDemandStatus().equals("Draft"))
				.collect(Collectors.toList());
		List<DemandMaster> demandMasterInDraftMode = demandService.findAll().stream()
				.filter(x -> x.getDemandStatus().equals("Draft")).collect(Collectors.toList());
		List<DemandMaster> dMaster = demandService.findByDemandedBy(u1.getSerno());
		List<DemandMaster> dMasterFin = demandService.findByDemandedByAndDemandStatusFinalised(u1.getSerno());
		mv.addObject("urole", u1.getRoles().iterator().next().getName());
		List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CodeHeadMaster> chmaster = codeHeadService.findAll();
		List<CodeHeadMaster> onlyahq = chmaster.stream().filter(c -> c.getCodeHeadType().equals("CENTRAL"))
				.collect(Collectors.toList());
		List<CodeHeadMaster> onlycmdstn = chmaster.stream().filter(c -> c.getCodeHeadType().equals("LOCAL"))
				.collect(Collectors.toList());

		List<DemandNoMaster> demandforcmd = demandNoMasterService.findByUnitCD(u1.getUnitId().getCommandId().getCommandCD());
		demandforcmd.stream().filter(x -> x.getDemandMaster().iterator().next().getDemandStatus().equals("Finalised"))
				.collect(Collectors.toList());

		for (User us : u) {
			if (us.getAuthLevel().equals("AHQ")) {
				mv.addObject("onlyahq", onlyahq);

			} else if (us.getAuthLevel().equals("CMD")) {
				mv.addObject("onlyahq", onlycmdstn);
				mv.addObject("forCmd", demandMasterInDraftMode);
			} else if (us.getAuthLevel().equals("UNIT")) {
				mv.addObject("onlyahq", onlycmdstn);

			}

		}
		List<DemandMaster> dm = demandService.findAll();
		List<ItemTypeMaster> itm = itemTypeService.findAll();
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		List<FinYearMaster> finYear = finYearService.findActiveFinYear();
		mv.addObject("finYear", finYear);
		mv.addObject("demandforcmd", demandforcmd);
		mv.addObject("fetchDemandNO", demandByUnitId);
		mv.addObject("demandmode", demandByUnitIdDraft);
		mv.addObject("demanddraft", dMaster);
		mv.addObject("findemand", dMasterFin);
		mv.addObject("demand", dm);
		mv.addObject("itemtype", itm);
		mv.addObject("codehead", chm);
		mv.addObject("urole",u1.getAuthLevel());
		mv.addObject("udetail",u1);

		return mv;

	}
	
	@GetMapping("/demandforperusalofcmd")
	public ModelAndView demandForPerusalForCmd(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("demandforperusalofcmd");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		List<DemandNoMaster> demandforcmd = demandNoMasterService.findByUnitCD(u1.getUnitId().getCommandId().getCommandCD()).stream().filter(x->x.getDemandLevel().equals("CMD")).collect(Collectors.toList());
		mv.addObject("udetail",u1);
		mv.addObject("urole",u1.getAuthLevel());
		mv.addObject("demandforcmd", demandforcmd);

		return mv;
	}
	
	@GetMapping("/demandforperusalofahq")
	public ModelAndView demandForPerusalForAHQ(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("demandforperusalofahq");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		System.out.println(u1.getAuthLevel());
		List<DemandNoMaster> demandforahq = demandNoMasterService.findAll().stream().filter(x->x.getDemandLevel().equals("AHQ")).collect(Collectors.toList());
		mv.addObject("urole",u1.getAuthLevel());
		mv.addObject("udetail",u1);
		mv.addObject("demandforahq", demandforahq);

		return mv;
	}
	

	@PostMapping("/submitdemand")
	public ModelAndView submitDemand(HttpServletRequest request, DemandMaster demandMaster,
			DemandNoMaster demandNoMaster, RedirectAttributes redirectAttribute) {

		demandService.saveDemandMethod(request, redirectAttribute, demandMaster, demandNoMaster);

		return new ModelAndView("redirect:/demand");
	}

	@PostMapping("/changedemandstatus")
	public ModelAndView changeDemandStatus(HttpServletRequest request, DemandMaster demandMaster,
			RedirectAttributes redirectAttribute) {
		demandService.updateAndSave(request, redirectAttribute);
		return new ModelAndView("redirect:/demand");
	}
	
	@PostMapping("/changedemandstatusforcmd")
	public ModelAndView changeDemandStatusForCMDLevel(HttpServletRequest request, DemandMaster demandMaster,
			RedirectAttributes redirectAttribute) {
		demandService.updateAndSaveForCMD(request, redirectAttribute);
		return new ModelAndView("redirect:/demandforperusalofcmd");
	}
	
	@PostMapping("/changedemandstatusforahq")
	public ModelAndView changeDemandStatusForAHQLevel(HttpServletRequest request, DemandMaster demandMaster,
			RedirectAttributes redirectAttribute) {
		demandService.updateAndSaveForAHQ(request, redirectAttribute);
		return new ModelAndView("redirect:/demandforperusalofahq");
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
			@RequestParam("itemTypeId") Integer itemTypeId) {
		return itemSubTypeService.findByItemTypeId(itemTypeId);
	}

	@GetMapping("/fetchitemtypebycodehead")
	public @ResponseBody List<Object[]> fetchItemTybeByCodeHead(HttpServletRequest request,
			@RequestParam("codeHead") Integer codeHead) {
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
	public String deleteQuery(@PathVariable("id") int id, RedirectAttributes redirectAttribute) {
		demandService.deleteById(id);
		redirectAttribute.addFlashAttribute("successdelete", "Item ID " + id + " is deleted");
		return "redirect:/demand";
	}

	@GetMapping(value = "deletedemandnoindraft/{id}")
	public String deleteDemandNoInDraft(@PathVariable("id") int id, RedirectAttributes redirect) {
		demandNoMasterService.deleteById(id);
		redirect.addFlashAttribute("successdeleteofdraftdemand", "Demand No is Deleted");
		return "redirect:/demand";
	}

}
