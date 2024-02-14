package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class HtmlController {
	@RequestMapping(path = "/homedemo", method = RequestMethod.GET)
	public String homedemo() {

	
	
		return "homedemo";
	}
	

	@RequestMapping(path = "/event", method = RequestMethod.GET)
	public String event() {

		return "event";
	}
	
	
	
	@RequestMapping(path = "/eventtest", method = RequestMethod.POST)
	public String mhp(HttpSession session, String username) {

		session.setAttribute("uname", username);
		return "redirect:/question";
	}
	@RequestMapping(path = "/acountsaitouroku", method = RequestMethod.GET)
			public String acountsaitouroku() {

        return "acountsaitouroku";
    }
	@RequestMapping(path = "/acountzyouhou", method = RequestMethod.GET)
	public String acountzyouhou() {

return "acountzyouhou";
}
	@RequestMapping(path = "/kamoku", method = RequestMethod.GET)
	public String kamoku() {

return "kamoku";
}
	@RequestMapping(path = "/kamoku2", method = RequestMethod.GET)
	public String kamoku2() {

return "kamoku2";
}
	@RequestMapping(path = "/kamokukanryou", method = RequestMethod.GET)
	public String kamokukanryou() {

return "redirect:kamokukanryou";
}
	@RequestMapping(path = "/kamokutouroku", method = RequestMethod.GET)
	public String kamokutouroku() {

return "kamokutouroku";
}
	@RequestMapping(path = "/memo", method = RequestMethod.GET)
	public String memo() {

return "memo";
}
	@RequestMapping(path = "/memoeturan", method = RequestMethod.GET)
	public String memoeturan() {

return "memoeturan";
}
	@RequestMapping(path = "/memokanryou", method = RequestMethod.GET)
	public String memokanryou() {

return "memokanryou";
}
	@RequestMapping(path = "/memosentaku", method = RequestMethod.GET)
	public String memosentaku() {

return "memosentaku";
}
	@RequestMapping(path = "/memotouroku", method = RequestMethod.GET)
	public String memotouroku() {

return "memotouroku";
		
}
	@RequestMapping(path = "/nikka", method = RequestMethod.GET)
	public String nikka() {

return "nikka";
}
	@RequestMapping(path = "/nikkakanryou", method = RequestMethod.GET)
	public String nikkakanryou() {

return "nikkakanryou";
}
	@RequestMapping(path = "/nikkasentaku", method = RequestMethod.GET)
	public String nikkasentaku() {

return "nikkasentaku";
}
	@RequestMapping(path = "/nikkatouroku", method = RequestMethod.GET)
	public String nikkatouroku() {

return "nikkatouroku";
}
	@RequestMapping(path = "/notekekka", method = RequestMethod.GET)
	public String notekekka() {
		

return "notekekka";
}
	@RequestMapping(path = "/notekensaku", method = RequestMethod.GET)
	public String notekensaku() {

return "notekensaku";
}
	@RequestMapping(path = "/sinkitouroku", method = RequestMethod.GET)
	public String sinkitouroku() {

return "sinkitouroku";
}
	@RequestMapping(path = "/taikai", method = RequestMethod.GET)
	public String taikai() {

return "taikai";
}

}
