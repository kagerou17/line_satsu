package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class HtmlController {

	
	@RequestMapping(path = "/homedemo", method = RequestMethod.GET)
	public String home() {

		return "homedemo";
	}
	@RequestMapping(path = "/event", method = RequestMethod.GET)
	public String event() {

		return "event";
	}
	@RequestMapping(path = "/kamokutouroku", method = RequestMethod.GET)
	public String kamokutouroku() {

		return "kamokutouroku";
	}
	@RequestMapping(path = "/memo", method = RequestMethod.GET)
	public String memo() {

		return "memo";
	}
	
	@RequestMapping(path = "/nikkasentaku", method = RequestMethod.GET)
	public String nikka() {

		return "nikkasentaku";
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(path = "/eventtest", method = RequestMethod.POST)
	public String mhp(HttpSession session, String username) {

		session.setAttribute("uname", username);
		return "redirect:/question";
	}
}
