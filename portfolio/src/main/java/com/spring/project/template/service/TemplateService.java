package com.spring.project.template.service;

import java.util.List;
import java.util.Map;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.template.service
 * @클래스명   :TemplateService
 * @태그명    :
 */
public interface TemplateService {
	/*조회 */
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
	/*입력*/
	void insertBoard(Map<String, Object> map) throws Exception;
	/*단건 조회*/
	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;
	/*수정*/
	void updateBoard(Map<String, Object> map) throws Exception;
	/*삭제*/
	void deleteBoard(Map<String, Object> map) throws Exception;
}
