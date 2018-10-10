package com.jy.springboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jy.springboard.service.UserService;

@RestController
public class JsonController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="user12/idcheck", method=RequestMethod.GET)
	public Map<String, Object> idcheck(@RequestParam("email") String email, Model model){
		Map<String, Object> map = new HashMap<String,Object>();
		String result = userService.idcheck(email);
		map.put("result", result==null);
		return map;
	}
}
