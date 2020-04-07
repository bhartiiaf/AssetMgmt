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

import com.unifiedweb360.modal.user.User;
import com.unifiedweb360.service.UserService;
@Controller
public class IndexController {
	@Autowired
	UserService userService;
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
		List<User> u = userService.findUserByUserName(uname);
		for(User us:u)
		{
			request.getSession().setAttribute("userDetails", us.getUsername());
		}
		//System.out.println(request.getSession().getAttribute("userDetails"));


		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		String strDate= formatter.format(date);  
		mv.addObject("dateval",strDate);  
		mv.addObject("udetail",request.getSession().getAttribute("userDetails"));
		return mv;
	}
	
}
