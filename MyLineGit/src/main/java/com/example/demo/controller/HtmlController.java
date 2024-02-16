package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Acount;
import com.example.demo.entity.Kamoku;
import com.example.demo.repository.AcountRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.KamokuRepository;
import com.example.demo.repository.NikkaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HtmlController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	AcountRepository acountripository;
	@Autowired
	EventRepository eventripository;
	@Autowired
	NikkaRepository nikkaripository;
	
	@Autowired
	KamokuRepository kamokuripository;
	
	@RequestMapping(path = "/homedemo", method = RequestMethod.GET)
	public String homedemo() {

	
	
		return "homedemo";
	}
	
	
	
	//科目登録html
	@RequestMapping(path = "/kamokutouroku", method = RequestMethod.GET)
	public String kamokutouroku() {

	return "kamokutouroku";
	}
	
	@RequestMapping(path = "/kamokutouroku/add", method = RequestMethod.POST)
	public String kamokutouroku1(Model model,Integer kamoku_id,String kamoku1,
			String kamoku2,String kamoku3,String kamoku4,String kamoku5,
			String kamoku6,String kamoku7) {
	
		System.out.println("ねねねねんえねねねねねねねね");	
		Kamoku kamoku = new Kamoku();
		
		kamoku.setKamoku_id(kamoku_id);
		kamoku.setKamoku1(kamoku1);
		kamoku.setKamoku2(kamoku2);
		kamoku.setKamoku3(kamoku3);
		kamoku.setKamoku4(kamoku4);
		kamoku.setKamoku5(kamoku5);
		kamoku.setKamoku6(kamoku6);
		kamoku.setKamoku7(kamoku7);
		//社員テーブルにINSERT文を発行するRepositoryの呼び出し。
	
		kamokuripository.save(kamoku);
		System.out.println("でたよー");		
		return "kamokukanryou";
	}
	
	
	
	
	
	//新規登録のメソッド
	@RequestMapping(path = "/sinkitouroku/{lineUserId}", method = RequestMethod.GET)
	public String sinkitouroku(@PathVariable String lineUserId, Model model) {

		model.addAttribute("lineUserId", lineUserId);
		return "sinkitouroku";
	}
	@RequestMapping(path = "/sinkitouroku/{lineUserId}", method = RequestMethod.POST)
	public String sinkitouroku1(@PathVariable String lineUserId, Model model,int acount_id,String acount_name,String password) {

		model.addAttribute("lineUserId", lineUserId);
		
		System.out.println("あああああああああああ");	
		Acount acount = new Acount();
		
		acount.setAcount_id(acount_id);
		acount.setAcount_name(acount_name);
		acount.setPass(password);
		System.out.println("いけた");
		
		acountripository.save(acount);
		return "sinkitouroku";
	}
	
	
	
	@RequestMapping(path = "/event", method = RequestMethod.GET)
	public String event() {

		
		return "event";
	}
//	@RequestMapping(path = "/event", method = RequestMethod.POST)
//	public String eventgo(String date,String text,Model model) {
//
//		//社員テーブルにデータを登録する。
//				//Syainのインスタンスを生成し、idとnameとemailをセットする。
//				
//		            Event event = new Event();
//				syain.setId(Integer.parseInt(id));
//				syain.setName(name);
//				syain.setEmail(email);
//				
//				//社員テーブルにINSERT文を発行するRepositoryの呼び出し。
//				syainDbRepository.save(syain);
//		
//		return "event";
//	}
	
	
	
	
	
	
	
	
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
	@RequestMapping(path = "/kamoku", method = RequestMethod.POST)
	public String kamoku(String gakka,String gakunen,HttpSession session) {

		session.setAttribute("gakka", gakka);
		session.setAttribute("gakunen", gakunen);

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
	
	
	@RequestMapping(path = "/taikai", method = RequestMethod.GET)
	public String taikai() {

return "taikai";
}

}