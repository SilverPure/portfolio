package com.spring.project.user.service;

import java.util.List;
import java.util.Map;

import com.spring.project.common.common.CommandMap;

/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 13.
 * @페케이지명 :com.spring.project.user.service
 * @클래스명   :LoginService
 * @태그명    :
 */
public interface LoginService {
	
	//로그인 체크
	Map<String, Object> loginProc(Map<String, Object> map) throws Exception;
	

}
