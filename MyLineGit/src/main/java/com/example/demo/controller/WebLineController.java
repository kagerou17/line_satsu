package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



public class WebLineController {

	//画面表示用
			@RequestMapping(path = "/homedemo", method = RequestMethod.GET)
			public String homedemo() {
 
			
			
				return "homedemo";
			}
}
