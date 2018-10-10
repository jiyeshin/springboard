package com.jy.springboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@RequestMapping(value = "/user12/register", method = RequestMethod.GET)
	//단순 page이동은 리턴 없이 생성해도 됨.
	//뷰 이름이 user12/register가 됨. 
	public void registre(Model model) {
	}
}
