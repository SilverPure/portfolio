package com.spring.project.process.service;

import java.util.List;
import java.util.Map;

import com.spring.project.common.common.CommandMap;

public interface ProcessService {
	//크롤링 수집
	void createCrawling(Map<String, Object> map) throws Exception;
	//크롤링 배치
	void insertRealTimeSearch() throws Exception;
	//리스트 조회
	List<Map<String, Object>> selectRTSBoadList(Map<String, Object> commandMap) throws Exception;
	//네이버 실시간 검색어 리스트 조회
	List<Map<String, Object>> rtsn(Map<String, Object> commandMap) throws Exception;
	//다음 실시간 검색어 리스트 조회
	List<Map<String, Object>> rtsd(Map<String, Object> commandMap) throws Exception;
	//전체 목록 조회
	List<Map<String, Object>> selectDetail(Map<String, Object> map) throws Exception;
	
	
}
