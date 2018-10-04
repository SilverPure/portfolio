package com.spring.project.process.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.common.AbstractDAO;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 24.
 * @페케이지명 :com.spring.project.process.dao
 * @클래스명   :ProcessDAO
 * @태그명    :
 */
@Repository("processDAO")
public class ProcessDAO extends AbstractDAO {
	/*실시간 검색어 입력*/
	@Transactional
	public void insertRealTime(Map<String, Object> map) throws Exception{
		insert("processlogic.insertRealTime", map);
	}
	/*실시간 검색 클롤링 데이터 입력*/
	@Transactional
	public void insertRow(Map<String, Object> map) throws Exception{

		insert("processlogic.insertRow", map);
	}
	//실시간 검색어 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectRTSTable(Map<String, Object> map) throws Exception{
		
		return (List<Map<String, Object>>) selectList("processlogic.selectKeyword",map);
	}
	
	//보드 리스트 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectRTSBoadList(Map<String, Object> map) throws Exception{
		return selectList("processlogic.selectRTSBoard",map);
	}
	
	//전체 목록 조회 
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectDetail(Map<String, Object> map) throws Exception {
		return selectList("processlogic.selectDetail",map);
	}
	
}
