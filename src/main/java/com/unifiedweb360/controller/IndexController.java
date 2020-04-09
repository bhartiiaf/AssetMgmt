package com.unifiedweb360.controller;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unifiedweb360.modal.user.Role;
import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.service.UserService;
import com.unifiedweb360.service.usermgmt.RoleService;
@Controller
public class IndexController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@GetMapping({"/"})
	public ModelAndView getArticleView(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("login");
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		String strDate= formatter.format(date);  
		mv.addObject("dateval",strDate);  
		return mv;
	}
	
	@GetMapping({"/welcome"})
	public ModelAndView getWelcomePage(HttpServletRequest request, HttpSession session)
	{
		ModelAndView mv = new ModelAndView("welcome");
		Principal principal = request.getUserPrincipal();
		String uname = principal.getName();
		User u1 = userService.findByUsername(uname);
		mv.addObject("udetail",u1);
		mv.addObject("urole",u1.getRoles().iterator().next().getName());
		return mv;
	}
	
}
