package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class HtmlController {

	
	@RequestMapping(path = "/memoeturan", method = RequestMethod.GET)
	public String nikka() {

		return "memoeturan";
	}
	@RequestMapping(path = "/event", method = RequestMethod.GET)
	public String event() {

		return "event";
	}
	
	
	
	
//	@RequestMapping(path = "/kamokutouroku", method = RequestMethod.GET)
//	public String kamoku() {
//
//		return "kamokutouroku";
//	}
	
	
	

	
	
	
	@RequestMapping(path = "/eventtest", method = RequestMethod.POST)
	public String mhp(HttpSession session, String username) {

		session.setAttribute("uname", username);
		return "redirect:/question";
	}
}
