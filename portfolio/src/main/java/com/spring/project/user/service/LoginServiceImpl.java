package com.spring.project.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.project.user.dao.LoginDAO;

/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 13.
 * @페케이지명 :com.spring.project.user.service
 * @클래스명   :LoginServiceImpl
 * @태그명    :
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	private static final Logger logger=LoggerFactory.getLogger(LoginServiceImpl.class); 
	
	@Resource(name="loginDAO")
	private LoginDAO loginDAO;
	
	//로그인 처리
	@Override
	public Map<String, Object> loginProc(Map<String, Object> map) throws Exception{
		String getKakaoId=(String)map.get("kakao_id");
		String getEmail=(String)map.get("email");
		Map<String, Object> result = null;
		
		Map<String, Object> chk = loginDAO.loginCheck(map);
		String cnt = String.valueOf(chk.get("count"));
		logger.info("cnt================> "+cnt);
		//사용자 조회가 0 이고 카카오아이디가 null이 않이면 카카오 로그인 이므로 카카오 정보를 인서트  
		Integer intCnt=Integer.parseInt(cnt);
		if(0==intCnt) {
			loginDAO.loginInster(map);
		}
		
		result=(Map<String, Object>) loginDAO.loginSelect(map);
		result.put("cnt", cnt);
		
		return result;
	}

}
