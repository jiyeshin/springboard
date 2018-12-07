package com.pk.server.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

//사용자 요청을 처리하기 위한 메소드의 모양을 선언 
public interface MemoService {
	public Map<String, Object> memolist(HttpServletRequest request);
	
	//상세보기를 위한 메소드 
	public Map<String, Object> memodetail(HttpServletRequest request);
	
	//상세보기를 위한 메소드 
	public Map<String, Object> memodelete(HttpServletRequest request);
		
	// 데이터 삽입을 위한 메소드 
	// 파일 삽입을 위해서 MultipartHttpServletRequest 사용.
	public Map<String, Object> memoinsert (MultipartHttpServletRequest request);
	

}
