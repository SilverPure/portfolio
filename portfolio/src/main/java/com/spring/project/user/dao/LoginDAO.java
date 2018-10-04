package com.spring.project.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.common.AbstractDAO;

/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 13.
 * @페케이지명 :com.spring.project.user.dao
 * @클래스명   :LoginDAO
 * @태그명    :
 */
@Repository("loginDAO")
public class LoginDAO extends AbstractDAO {
	
	private static final Logger logger=LoggerFactory.getLogger(LoginDAO.class);
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> loginCheck(Map<String, Object> map) throws Exception{
		logger.info("loginCheck============>"+map.toString());
		return (Map<String, Object>) selectOne("loginProc.checkId", map);
	}
	
	@Transactional
	public void loginInster(Map<String, Object> map) {
		insert("loginProc.userInst", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loginSelect(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("loginProc.userSel", map);

	}
}
