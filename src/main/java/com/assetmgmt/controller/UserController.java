package com.assetmgmt.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assetmgmt.modal.user.User;
import com.assetmgmt.service.SecurityService;
import com.assetmgmt.service.UserService;
import com.assetmgmt.service.usermgmt.RoleService;



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    
    
    @Autowired
    RoleService roleService;

   
    
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("userrole",roleService.findAll());
        List<User> u = userService.findAllUser();
       
        model.addAttribute("userdetail",u);
        return "registration";
    }

   
    @PostMapping("/getregistration")
    public ModelAndView getRegistered(User userForm,HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        List<User> u = userService.findUserBySerno(userForm.getSerno());
        if(u.size() == 0)
        {
        	userService.save(userForm);
           // securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
            redirectAttributes.addFlashAttribute("regsuccessmessage", "You are registered");
            return  new ModelAndView("redirect:/registration");
        }
        else
        {
            redirectAttributes.addFlashAttribute("regfailmessage", "Hey! You are already registered");
        	return  new ModelAndView("redirect:/registration");
        }

    }
    
    

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

	
	/*
	 * @GetMapping({"/", "/welcome"}) public String welcome(Model model) { return
	 * "article"; }
	 */
	
}