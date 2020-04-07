package com.unifiedweb360.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unifiedweb360.modal.master.CodeHeadMaster;
import com.unifiedweb360.modal.master.DemandMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.service.UserService;
import com.unifiedweb360.service.master.CodeHeadMasterService;
import com.unifiedweb360.service.master.DemandMasterService;
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
	
	
	@GetMapping("/demand")
	public ModelAndView getDemandPage()
	{
		ModelAndView mv = new ModelAndView("demandmaster");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		List<User> u = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CodeHeadMaster> chmaster = codeHeadService.findAll();
		List<CodeHeadMaster> onlyahq = chmaster.stream().filter(c -> c.getCodeHeadType().equals("CENTRAL"))
				.collect(Collectors.toList());
		List<CodeHeadMaster> onlycmdstn = chmaster.stream().filter(c -> c.getCodeHeadType().equals("LOCAL"))
				.collect(Collectors.toList());
		
		for(User us : u)
		{
			if(us.getAuthLevel().equals("AHQ"))
			{
				mv.addObject("onlyahq",onlyahq);

			}
			else if(us.getAuthLevel().equals("COMMAND"))
			{
				mv.addObject("onlyahq",onlycmdstn);
			}
			else if(us.getAuthLevel().equals("UNIT"))
			{
				mv.addObject("onlyahq",onlycmdstn);

			}
			
		}
		List<DemandMaster> dm = demandService.findAll();
		List<ItemTypeMaster> itm = itemTypeService.findAll();
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		mv.addObject("demand",dm);
		mv.addObject("itemtype",itm);
		mv.addObject("codehead",chm);
		return mv;
	}
	
	@PostMapping("/submitdemand")
	public ModelAndView submitDemand(HttpServletRequest request, DemandMaster demandMaster,
			RedirectAttributes redirectAttribute)
	{
		demandService.save(demandMaster);
		return new ModelAndView("redirect:/demand");
	}
	
	
	@GetMapping("/fetchcodeheaddata") 
	  public @ResponseBody CodeHeadMaster fetchCodeHeadDataByDesc(CodeHeadMaster codeHeadMaster ,@RequestParam("codeHead") String codeHead) 
	  { 
		CodeHeadMaster am=new CodeHeadMaster();
		codeHeadService.findById(Integer.parseInt(codeHead));
	    return am;
	  }
	
	
	@GetMapping("/codeheaddata")
	public @ResponseBody List<Object[]> fetchCodeHeadData(HttpServletRequest request,
			@RequestParam("codeHead") String codeHead)
	{
		return codeHeadService.findAllById(Integer.parseInt(codeHead));
	}
	
	@GetMapping("/fetchsubitem")
	public @ResponseBody List<Object[]> fetchSubItem(HttpServletRequest request,
			@RequestParam("itemTypeId") Integer itemTypeId)
	
	{
		System.out.println(itemTypeId);
		return itemSubTypeService.findByItemTypeId(itemTypeId);
	}
	
	

	}
