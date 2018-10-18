package com.jy.springboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jy.springboard.domain.User12;
import com.jy.springboard.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user12/register", method = RequestMethod.GET)
	//단순 page이동은 리턴 없이 생성해도 됨.
	//뷰 이름이 user12/register가 됨. 
	public void register(Model model) {
	}
	

	
	@RequestMapping(value = "/user12/register", method = RequestMethod.POST)
	public String register(MultipartHttpServletRequest request) {
		userService.register(request);
		//회원가입을 처리하고 메인으로 리다이렉트. /는 최상위 폴더를 가리키므로 메인으로 간다. 
		//데이터베이스에 변화를 주면 반드시 리다이렉트!!!	
		return "redirect:/";
	}
	
	//로그인 페이지로 이동 
	@RequestMapping(value = "/user12/login", method = RequestMethod.GET)
	public void login(Model model) {
	}
	
	//로그인 처리 
	@RequestMapping(value = "/user12/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, RedirectAttributes attr) {
		User12 user12 = userService.login(request);
		if(user12 == null) {
			//로그인 실패하면 user12에 null이라고 저장 
			session.setAttribute("user12", null);
			
			//RedirectAttributes는 리다이렉트 될 때 한 번만 데이터를 전달함. 
			//login.jsp 에서 보낸 msg의 값을 여기서 정할 수 있다.
			attr.addFlashAttribute("msg", "없는 이메일 이거나 잘못된 비밀번호입니다.");
			return "redirect:login";
			
		}else {
			//로그인 성공하면 user12에 로그인 정보를 저장 
			session.setAttribute("user12", user12);
			
			//../는 현재보다 한단계 위. 즉, 여기서는 /user12/login이 지워지면서 메인페이지
			return "redirect:../"; 
		}
	}
	
	//로그아웃 처리 
	@RequestMapping(value = "/user12/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../"; 
	}
	
	
	
}
