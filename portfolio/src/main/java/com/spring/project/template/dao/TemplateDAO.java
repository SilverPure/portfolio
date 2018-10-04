package com.spring.project.template.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.project.common.common.AbstractDAO;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.template.dao
 * @클래스명   :TemplateDAO
 * @태그명    :
 */
@Repository("templateDAO")
public class TemplateDAO extends AbstractDAO {
	
	private Logger logger = LoggerFactory.getLogger(TemplateDAO.class); 
	
	/*조회*/
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return selectList("template.selectBoardList", map);

	}
	/*max 조회*/
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMax(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("template.selectMax", map);
	}
	/*입력*/
	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("template.insertBoardList", map);
	}
	/*상세보기*/
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map){
		return (Map<String, Object>) selectOne("template.selectDetail", map);
	}
	/*수정*/
	public void updateBoard(Map<String, Object> map) throws Exception{
	    update("template.updateBoard", map);
	}
	/*삭제*/
	public void deleteBoard(Map<String, Object> map) throws Exception{
	    update("template.deleteBoard", map);
	}
	/*jsoupProc*/
	public List<Map<String, Object>> jsoupProc(Map<String, Object> map){
		return null;
	}
	
}
