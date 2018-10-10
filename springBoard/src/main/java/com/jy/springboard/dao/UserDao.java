package com.jy.springboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//select 구문을 수행하는 메소드는 결과가 0개나 1개인지 아니면 
	//2개 이상도 나올 수 있는지 확인하고 
	//0개나 1개면 resultType으로 리턴타입을 만들고 
	//2개 이상 나올 수 있으면 resultType의 List 로 리턴타입을 만듦. 
	//매개변수가 있으면 매개변수를 만들고, 없으면 안만들면 됨. 
	
	
	//아이디 중복 체크 메소드 
	public String idCheck(String email) {
		//이름을 확인하고 파라미터 존재 여부를 확인. 
		return sqlSession.selectOne("user12.idcheck", email);
	}
}
