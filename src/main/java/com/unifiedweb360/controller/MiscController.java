package com.unifiedweb360.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unifiedweb360.modal.master.CodeHeadMaster;
import com.unifiedweb360.modal.master.ItemTypeMaster;
import com.unifiedweb360.service.master.CodeHeadMasterService;
import com.unifiedweb360.service.master.ItemTypeMasterService;

@Controller
public class MiscController {
	@Autowired
	CodeHeadMasterService codeHeadService;
	
	@Autowired
	ItemTypeMasterService itemTypeService;
	
	@GetMapping("/itemtype")
	public ModelAndView getItemType()
	{
		ModelAndView mv = new ModelAndView("itemtypemaster");
		List<ItemTypeMaster> itm = itemTypeService.findAll();
		mv.addObject("itetype",itm);
		return mv;
	}
	
	@PostMapping("/submititemtype")
	public ModelAndView submitItemType(HttpServletRequest request, ItemTypeMaster itemTypeMaster,
			RedirectAttributes redirectAttribute)
	{
		List<ItemTypeMaster> itm = itemTypeService.findByItemDescription(request.getParameter("itemDescription"));
		if(itm.size() == 0)
		{
			itemTypeService.save(itemTypeMaster);
			redirectAttribute.addFlashAttribute("success","Item Type Saved");
			return new ModelAndView("redirect:/itemtype");
		}
		else
		{
			redirectAttribute.addFlashAttribute("failure","Item Type: " + request.getParameter("itemDescription")+ " Already Exist");
			return new ModelAndView("redirect:/itemtype");
		}
	}

	
	
	@GetMapping("/codehead")
	public ModelAndView getCodeHead()
	{
		ModelAndView mv = new ModelAndView("codehead");
		List<CodeHeadMaster> chm = codeHeadService.findAll();
		mv.addObject("codehead",chm);
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

}