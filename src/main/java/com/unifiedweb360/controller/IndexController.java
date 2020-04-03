package com.unifiedweb360.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class IndexController {
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

	
	
	
	
}
