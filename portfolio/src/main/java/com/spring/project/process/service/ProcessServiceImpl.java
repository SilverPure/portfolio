package com.spring.project.process.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.project.common.jsoup.JsoupSSL;
import com.spring.project.process.dao.ProcessDAO;

import sun.util.logging.resources.logging;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 24.
 * @페케이지명 :com.spring.project.process.service
 * @클래스명   :ProcessServiceImpl
 * @태그명    :
 */
@Service("processService")
public class ProcessServiceImpl implements ProcessService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);
	
	@Resource(name="processDAO")
	private ProcessDAO processDAO;
	
	//전체 목록 조회
	@Override
	public List<Map<String, Object>> selectDetail(Map<String, Object> map) throws Exception{
		return processDAO.selectDetail(map);
	}
	
	//네이버 실시간 검색어 리스트 조회
	@Override
	public List<Map<String, Object>> rtsn(Map<String, Object> map) throws Exception{
		String site="nav";
		map.put("site_code", site);
		logger.info("site_code=> "+map.toString());
		logger.info("processDAO.selectRTSTable(map)==>"+processDAO.selectRTSTable(map));
		return processDAO.selectRTSTable(map);
	}
	//다음 실시간 검색어 리스트 조회
	@Override
	public List<Map<String, Object>> rtsd(Map<String, Object> map) throws Exception{
		String site="dau";
		map.put("site_code", site);
		return processDAO.selectRTSTable(map);
	}
	//마더 테이블 리스트 조회
	@Override
	public List<Map<String, Object>> selectRTSBoadList(Map<String, Object> map) throws Exception{
		return processDAO.selectRTSBoadList(map);
	}
	//삭제 예정 메소드
	@Override
	public void createCrawling(Map<String, Object> map) throws Exception {
		
		HashMap<String, Object> setVal = new HashMap<>();
		/*jsoup 사용을 위한 USER_AGENT*/
		String agent = (String)map.get("browser");
		String connUrl = (String) map.get("google");
		//사이트 체크 
		int chkCode = ProcessUtil.chkSite(connUrl,agent);
		/* 200 성공
		 * 4** 실패
		 * */
        if(200 == chkCode) {
        	setVal.put("q", (String)map.get("keyword"));
        	String targetURL = connUrl.concat("/search?");
        	ProcessUtil.GoogleLogicProcess(setVal,targetURL,agent);
        }else{
        	
        }
		        
        return;
	}
	//실시간 검색어 프로세스 로직
	@Transactional
	@Override
	public void insertRealTimeSearch() throws Exception {
		
		String conn_url_nav="https://datalab.naver.com/keyword/realtimeList.naver?where=main";
		String conn_url_dau="https://www.daum.net";
		String conn_url_goo="https://www.google.co.kr/search?";
		String user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
 		String user_id="autoBot";
 		String bTime=null;
 		Connection conn=null;
 		Document doc=null;
 		Elements getRank=null;
 		Elements rowStr=null;
		List<Map<String, Object>> rtsResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> map=null;
		//실시간 검색 키워드 체크를 위해서 DB의 최근 검색 키워드를 가져 온다.
		//List<Map<String, Object>> chkRTS = processDAO.selectRTSTable(map);
 		
		try {
 			//2. SSL 체크
			JsoupSSL.setSSL();
			
			//naver 실시간
			conn = Jsoup
				  .connect(conn_url_nav)
                  .header("Content-Type", "application/json;charset=UTF-8")
                  .userAgent(user_agent)
                  .ignoreContentType(true);
	        doc = conn.get();
	        
	        getRank = doc.getElementsByClass("keyword_rank select_date");
	        //기준 시간 가져 오기
	        String getTime = getRank.select("strong").text();
	        //절삭 되는 index값 구하기
	        int cutidx=getTime.indexOf(")");
	        //시간 값 구하기 
	        bTime = getTime.substring(cutidx).replace("기준", "").replace(")", "");
	        //랭크 리스트의 내용의 태그와 속성을 지정
	        rowStr = getRank.select("li.list");
	        //해당 row의 size만큼 돌면서  태그의 value값을 map에 put
		        for(int i=0;i<rowStr.size();i++) {
		        	HashMap<String, Object> rowString = new HashMap<>();
		        	String _rank = rowStr.eq(i).select("em.num").text();
		        	String _keyword = rowStr.eq(i).select("span.title").text();
		        	rowString.put("rank", _rank);
		        	rowString.put("keyword", _keyword);
		        	rowString.put("stand_time", bTime);
		        	rowString.put("site_code","nav");
		        	rowString.put("inst_id", user_id);
		        	
		        	processDAO.insertRealTime(rowString);
		        }
	        
	        //daum 실시간
	        conn = Jsoup
	                .connect(conn_url_dau)
	                .header("Content-Type", "application/json;charset=UTF-8")
	                .userAgent(user_agent)
	                .ignoreContentType(true);
	        doc = conn.get();
	        
	        //랭크 리스트의 내용의 태그와 속성을 지정
	        getRank = doc.getElementsByClass("list_hotissue issue_row list_mini");
	        rowStr = getRank.select("div.roll_txt");
	        //해당 row의 size만큼 돌면서  태그의 value값을 map에 put
	        for(int i=0;i<rowStr.size();i++) {
	        	HashMap<String, Object> rowString = new HashMap<>();
	        	String _rank = rowStr.eq(i).select("span.ir_wa").text().replace("위", "");
	        	String _keyword = rowStr.eq(i).select("span.txt_issue").text();
	        	rowString.put("rank", _rank);
	        	rowString.put("keyword", _keyword);
	        	rowString.put("stand_time", bTime);
	        	rowString.put("site_code","dau");
	        	rowString.put("inst_id", user_id);
	        	processDAO.insertRealTime(rowString);
	        }
	        
 			//네이버 실시간 검색어
	        List<Map<String, Object>> getRTSN = processDAO.selectRTSTable(map);
	        logger.info("getRTSN===> "+getRTSN.toString());
	        //네이버 실시간 검색어 리스트
	        for(int i=0;i<getRTSN.size();i++) {
	        	HashMap<String, Object> getRow =  (HashMap<String, Object>) getRTSN.get(i);
	        	logger.info("getRow========> "+getRow.toString());
	        	//사이트 코드가 nav일 경우
	        	if("nav".equals(getRow.get("site_code"))){
	        		//구글 검색
        			rtsResult=ProcessUtil.GoogleLogicProcess(getRow,conn_url_goo,user_agent);
        			logger.info("rtsResult========> "+rtsResult.toString());
	        		for(int x=0;x<rtsResult.size();x++) {
	        			HashMap<String, Object> rsRow=(HashMap<String, Object>) rtsResult.get(x);
	        			HashMap<String, Object> instRow = new HashMap<>();
	        			instRow.put("row_code",rsRow.get("row_code"));
	        			instRow.put("title",rsRow.get("title"));
	        			instRow.put("headline",rsRow.get("list"));
	        			instRow.put("content",rsRow.get("content"));
	        			instRow.put("press",rsRow.get("press"));
	        			instRow.put("url",rsRow.get("url"));
	        			instRow.put("rank",rsRow.get("rank"));
	        			instRow.put("site_code","nav");
	        			instRow.put("depth",x);
	        			instRow.put("inst_id",user_id);
	        			instRow.put("user_code",user_id);
	        			instRow.put("stand_time",rsRow.get("stand_time"));
	        			processDAO.insertRow(instRow);
	        		}
	        	}
	        }
	        //다음 실시간 검색어
 			//{b_time="", rank="", site_code=N, keyword=""}
	        List<Map<String, Object>> getRTSD = processDAO.selectRTSTable(map);
	        for(int i=0;i<getRTSD.size();i++) {
	        	HashMap<String, Object> getRow =  (HashMap<String, Object>) getRTSD.get(i);
	        	if("dau".equals(getRow.get("site_code"))){
	        		rtsResult=ProcessUtil.DaumLogicProcess(getRow,conn_url_goo,user_agent);
	        		for(int x=0;x<rtsResult.size();x++) {
	        			HashMap<String, Object> rsRow =  (HashMap<String, Object>) rtsResult.get(x);
	        			HashMap<String, Object> instRow = new HashMap<>();
	        			instRow.put("row_code",rsRow.get("row_code"));
	        			instRow.put("title",rsRow.get("title"));
	        			instRow.put("headline",rsRow.get("list"));
	        			instRow.put("content",rsRow.get("content"));
	        			instRow.put("press",rsRow.get("press"));
	        			instRow.put("url",rsRow.get("url"));
	        			instRow.put("rank",rsRow.get("rank"));
	        			instRow.put("site_code","nav");
	        			instRow.put("depth",x);
	        			instRow.put("inst_id",user_id);
	        			instRow.put("user_code",user_id);
	        			instRow.put("stand_time",rsRow.get("stand_time"));
	        			processDAO.insertRow(instRow);
	        		}
	        	}
	        }
 		}catch (Exception e) {
			e.printStackTrace();
		}
 		
	}
		
}


