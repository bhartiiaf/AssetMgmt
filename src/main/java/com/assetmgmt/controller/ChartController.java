package com.assetmgmt.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assetmgmt.modal.user.User;
import com.assetmgmt.service.UserService;
import com.assetmgmt.service.master.DemandNoMasterService;

@Controller
public class ChartController {
	@Autowired
	UserService userService;
	@Autowired
	DemandNoMasterService demandNoService;
	
	
	@GetMapping("/alldemandbyunit")
	public @ResponseBody List<Object[]> demandListByUnitId(HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u = userService.findBySerno(uname);
		String uId = u.getUnitId().getUnitCD();
		return demandNoService.totalDemand(uId);
	}
	

}
