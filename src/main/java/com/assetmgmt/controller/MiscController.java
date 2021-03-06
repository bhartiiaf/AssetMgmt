package com.assetmgmt.controller;

import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assetmgmt.modal.master.CodeHeadMaster;
import com.assetmgmt.modal.master.DemandNoMaster;
import com.assetmgmt.modal.master.ItemSubTypeMaster;
import com.assetmgmt.modal.master.ItemTypeMaster;
import com.assetmgmt.modal.user.User;
import com.assetmgmt.service.BarCodeService;
import com.assetmgmt.service.UserService;
import com.assetmgmt.service.master.CodeHeadMasterService;
import com.assetmgmt.service.master.DemandMasterService;
import com.assetmgmt.service.master.DemandNoMasterService;
import com.assetmgmt.service.master.DemandStatusMasterService;
import com.assetmgmt.service.master.ItemSubTypeService;
import com.assetmgmt.service.master.ItemTypeMasterService;

@Controller
public class MiscController {
	@Autowired
	CodeHeadMasterService codeHeadService;
	
	@Autowired
	ItemTypeMasterService itemTypeService;
	
	@Autowired
	ItemSubTypeService itemSubTypeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DemandMasterService demandService;
	@Autowired
	DemandNoMasterService demandNoMasterService;
	

	
	
	
	@GetMapping("/itemtype")
	public ModelAndView getItemType(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("itemtypemaster");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		mv.addObject("urole",u1.getRoles().iterator().next().getName());
		mv.addObject("udetail",u1);

		List<ItemTypeMaster> itm = itemTypeService.findAll();
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		mv.addObject("itemtype",itm);
		mv.addObject("codehead",chm);
		return mv;
	}
	
	@GetMapping(value = "/barcode/{id}")
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(BarCodeService.getBarCodeImage(id, 200, 200));
		outputStream.flush();
		outputStream.close();
	}
	
	
	@PostMapping("/submititemtype")
	public ModelAndView submitItemType(HttpServletRequest request, ItemTypeMaster itemTypeMaster,
			RedirectAttributes redirectAttribute)
	{
		List<ItemTypeMaster> itm = itemTypeService.findByItemDescription(request.getParameter("itemDescription"));
		if(itm.size() == 0)
		{
			int dSize = request.getParameterValues("codeHeadMaster").length;
			List<ItemTypeMaster> ittm = new ArrayList<>();
			String itemDescription = request.getParameter("itemDescription");

			for (int i = 0; i < dSize; i++) {
				ItemTypeMaster im = new ItemTypeMaster();
				String codeHeadMaster = request.getParameterValues("codeHeadMaster")[i];
				im.setItemDescription(itemDescription);
				im.setCodeHeadMaster(codeHeadService.find(Integer.parseInt(codeHeadMaster)));
				im.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				im.setCreatedOn(new Date());
				ittm.add(im);
			}

			itemTypeService.saveAll(ittm);
			redirectAttribute.addFlashAttribute("success","Item Type Saved");
			return new ModelAndView("redirect:/itemtype");
		}
		else
		{
			redirectAttribute.addFlashAttribute("failure","Item Type: " + request.getParameter("itemDescription")+ " Already Exist");
			return new ModelAndView("redirect:/itemtype");
		}
	}
	
	@GetMapping("/itemsubtype")
	public ModelAndView getItemSubType(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("itemsubtypemaster");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		mv.addObject("urole",u1.getRoles().iterator().next().getName());
		mv.addObject("udetail",u1);

		List<ItemTypeMaster> itm = itemTypeService.findAll();
		List<ItemSubTypeMaster> subTypeItem = itemSubTypeService.findByOrder();

		
		mv.addObject("itemtype",itm);
		mv.addObject("subTypeItem", subTypeItem);
		return mv;
	}
	
	@PostMapping("/submititemsubtype")
	public ModelAndView submitItemSubType(HttpServletRequest request, ItemSubTypeMaster itemSubTypeMaster,
			RedirectAttributes redirectAttribute)
	{
		List<ItemSubTypeMaster> itm = itemSubTypeService.findBySubTypeDesc(request.getParameter("subTypeDesc"));
		if(itm.size() == 0)
		{
			
			itemSubTypeService.save(itemSubTypeMaster);
			redirectAttribute.addFlashAttribute("success","Item Sub Type Saved");
			return new ModelAndView("redirect:/itemsubtype");
		}
		else
		{
			redirectAttribute.addFlashAttribute("failure","Item Type: " + request.getParameter("subTypeDesc")+ " Already Exist");
			return new ModelAndView("redirect:/itemsubtype");
		}
	}
	
	
	@PostMapping("/updateItemSubType")
	public ModelAndView updateItemSubType(HttpServletRequest request,ItemSubTypeMaster itemSubTypeMaster,
			 RedirectAttributes redirectAttribute)
	{
		int itemTypeId = Integer.parseInt(request.getParameter("itemTypeId"));
		String subTypeDesc = request.getParameter("subTypeDesc");
		Double subItemPrice = Double.parseDouble(request.getParameter("subItemPrice"));
		int id = Integer.parseInt(request.getParameter("id"));
		List<ItemSubTypeMaster> istm = itemSubTypeService.findAllDataByIdForUpdate(id);
		istm.iterator().next().setItemTypeId(itemTypeService.find(itemTypeId));
		istm.iterator().next().setSubTypeDesc(subTypeDesc);
		istm.iterator().next().setSubItemPrice(subItemPrice);
		itemSubTypeService.save(itemSubTypeMaster);
		redirectAttribute.addFlashAttribute("updatesuccessitemsubtype","Item Sub Type : " +istm.iterator().
				next().getSubTypeDesc()+ " is updated successfully");
		return new ModelAndView("redirect:/itemsubtype");
	}
	

	@GetMapping(value = "fetchitemsubtypedata/{id}")
	public @ResponseBody List<Object[]> fetchItemSubTypeData(@PathVariable("id") int id) {
		return itemSubTypeService.findAllDataById(id);
	}
	
	@GetMapping(value = "deleteitemsubtype/{id}")
	public ModelAndView deleteItemSubType(@PathVariable("id") int id,RedirectAttributes redirect) {
		itemSubTypeService.deleteItemSubTypeById(id);
		redirect.addFlashAttribute("deletesuccess","Item Sub Type Deleted");
		return new ModelAndView("redirect:/itemsubtype");
	}
	
	@GetMapping(value = "deleteItemType/{id}")
	public ModelAndView deleteItemType(@PathVariable("id") int id,RedirectAttributes redirect) {
		itemTypeService.deleteItemTypeById(id);
		redirect.addFlashAttribute("deletesuccessitemtype","Item Type Deleted");
		return new ModelAndView("redirect:/itemtype");
	}
	
	
	
	
	@GetMapping("/codehead")
	public ModelAndView getCodeHead(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("codehead");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findBySerno(uname);
		mv.addObject("urole",u1.getRoles().iterator().next().getName());
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		mv.addObject("codehead",chm);
		mv.addObject("udetail",u1);

		return mv;
	}
	
	@PostMapping("/submitcodehead")
	public ModelAndView submitCodeHead(HttpServletRequest request, CodeHeadMaster codeHeadMaster,
			RedirectAttributes redirectAttribute)
	{
		List<CodeHeadMaster> chm = codeHeadService.findByCodeHeadDescription(request.getParameter("codeHeadDescription"));
		if(chm.size() == 0)
		{
			codeHeadService.save(codeHeadMaster);
			redirectAttribute.addFlashAttribute("success","Code Head Data Submitted");
			return new ModelAndView("redirect:/codehead");
		}
		else
		{
			redirectAttribute.addFlashAttribute("failure","Code Head: " + request.getParameter("codeHeadDescription")+ " Already Exist");
			return new ModelAndView("redirect:/codehead");
		}
	}
	
	
	@GetMapping("/notificationforcmd")
	public @ResponseBody List<DemandNoMaster> notificationForCmd() {
		List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
        List<DemandNoMaster> dnm = demandNoMasterService.findByUnitCD(u.iterator().next().getUnitId().getCommandId().getCommandCD());
        return dnm.stream().filter(x->x.getDemandLevel().equals("CMD")).collect(Collectors.toList()); 
	}
	
	@GetMapping("/notificationforahq")
	public @ResponseBody List<DemandNoMaster> notificationForAHQ() {
		List<User> u = userService.findUserBySerno(SecurityContextHolder.getContext().getAuthentication().getName());
        List<DemandNoMaster> dnm = demandNoMasterService.findAll();
        return dnm.stream().filter(x->x.getDemandLevel().equals("AHQ")).collect(Collectors.toList()); 
	}

}
