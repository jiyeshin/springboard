package com.jy.springboard.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.springboard.dao.UserDao;
import com.jy.springboard.domain.User12;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public String idcheck(String email) {
		
		return userDao.idCheck(email);
	}

	@Override
	public void register(MultipartHttpServletRequest request) {
		// 파라미터 읽어오기 
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		
		//파일은 getFile로 읽고 MultipartFile로 저장 
		MultipartFile image = request.getFile("image");
		
		//파일 업로드 처리 
		//업로드할 디렉토리를 문자열로 생성 
		String uploadPath = request.getRealPath("/userimage");
		
		//파일 이름 만들기 - 이름의 중복을 막기 위해 UUID와 원본파일을 합쳐서 생성  
		UUID uuid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		String filepath = uploadPath + "/" + uuid + filename; 
		
		//파일 업로드와 데이터베이스 작업 
		User12 user = new User12();
		File file = new File(filepath);
		try {
			user.setEmail(email);
			//비밀번호는 암호화 
			user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
			user.setNickname(nickname);
			user.setImage(uuid + "_"+filename);
			
			//파일 업로드
			image.transferTo(file);
			
			//데이터베이스 메소드 호출 
			userDao.register(user);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public User12 login(HttpServletRequest request) {
		//파라미터 읽기 
		String email=request.getParameter("email");
		String pw = request.getParameter("pw");
		
		//email에 해당하는 데이터 가져오기 
		User12 user12 = userDao.login(email);
		//null이 리턴되면 없는 이메일임.
		if(user12 !=null) {
			//있는 이메일이면 비번이 맞는지 확인 
			if(BCrypt.checkpw(pw, user12.getPw())) {
				//여기까지 왔다는 것은 이메일, 비번 디비와 일치한다는 뜻.
				//비번은 삭제 
				user12.setPw(null);
			}else {
				//있는 이메일이지만 비번이 틀렸으므로 null로 변경.
				//controller에서 페이지 이동시 틀렸는지 맞았는지 구분하는 방법은 user12가 null인지 아닌지로 할 수 있다.
				user12=null;
			}
		}
		return user12;
	}
	
	
}
