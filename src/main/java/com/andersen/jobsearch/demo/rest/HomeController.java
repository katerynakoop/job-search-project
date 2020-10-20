package com.andersen.jobsearch.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController
{
	@GetMapping("/")
	public String indexPage()
	{
		return "/index";
	}
}
