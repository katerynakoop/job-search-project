package com.andersen.jobsearch.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController
{
	@RequestMapping("/login")
	public String loginHandler(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model)
	{
		String errorMessage = null;
		if (error != null)
			errorMessage = "Username or Password is incorrect !! Or your account is banned. Contact the administrator: jobsearch_admin@gmail.com";

		if (logout != null)
			errorMessage = "You have been successfully logged out !!";
		
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		
		return "redirect:/login?logout=true";
	}
	
	@GetMapping("/login/success")
	public String redirectAfterSuccessfullLogin(Model model)
	{
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN"))
            return "redirect:/admin/dashboard";
        
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("EMPLOYEE"))
            return "redirect:/employee/dashboard";
            
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("EMPLOYER"))
            return "redirect:/employer/dashboard";
            
        return "index";
	}
}
