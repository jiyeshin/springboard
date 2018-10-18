package com.jy.springboard.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.springboard.domain.User12;

public interface UserService {

	// 아이디 중복 체크 메소드
	public String idcheck(String email);

	// 파일 업로드가 있을 때는 MultipartHttpServletRequest를 매개변수로 만듦.
	public void register(MultipartHttpServletRequest request);
	
	//로그인 처리 메소드 
	public User12 login(HttpServletRequest request);
}
