package com.pk.server;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pk.server.dao.MemoDAO;


@Controller
public class HomeController {
	
	//데이터 베이스 접속 정보 테스트
	@Autowired
	private MemoDAO memoDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*HashMap<String, Object>  map = new HashMap<>();
		map.put("num", 2);
		map.put("title", "타이틀");
		map.put("contents", "내용용용");
		map.put("regdate", "2018-12-05 11:38:00");
		map.put("image_path", "profile.png");
		//System.out.println(memoDAO.memoinsert(map));
		System.out.println(memoDAO.memodelete(3));*/
		
		return "home";
	}
	
	@RequestMapping(value = "memo/memoinsert", method = RequestMethod.GET)
	public String memoinsert(Locale locale, Model model) {	
		return "memo/memoinsert";
	}
	
}





