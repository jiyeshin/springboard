package com.pk.server;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.server.service.MemoService;

@RestController
//spring 4.0인지, jackson-databind 라이브러리가 포함되어 있는지 확인.
public class MemoRestController {
	//실제 서비스되는 프로젝트의 경우에는 여러 개의 서비스 객체가 포함되는 경우가 많다.
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(value="memo/memolist", method=RequestMethod.GET)
	public Map<String, Object> memoselect (HttpServletRequest request){	
		return memoService.memolist(request);
	}
	
	@RequestMapping(value="memo/memodetail", method=RequestMethod.GET)
	public Map<String, Object> memodetail (HttpServletRequest request){	
		return memoService.memodetail(request);
	}
	
	@RequestMapping(value="memo/memodelete", method=RequestMethod.POST)
	public Map<String, Object> memodelete (HttpServletRequest request){	
		return memoService.memodelete(request);
	}
	
	@RequestMapping(value="memo/memoinsert", method=RequestMethod.POST)
	public Map<String,Object> memoinsert(MultipartHttpServletRequest request){
		System.out.println("클라이언트 요청");
		return memoService.memoinsert(request);
	}
}
