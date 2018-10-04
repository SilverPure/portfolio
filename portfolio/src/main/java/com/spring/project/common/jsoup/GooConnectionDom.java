package com.spring.project.common.jsoup;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	
/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 7.
 * @페케이지명 :com.spring.project.common.jsoup
 * @클래스명   :GooConnRequest
 * @태그명    :구글 검색 connection
 */
public class GooConnectionDom {
	
	private static final Logger logger = LoggerFactory.getLogger(GooConnectionDom.class);
	
	//파라메타값을 파싱 하여 string으로 리턴
	public static String getParamEi(Map<String, Object> setVal) throws Exception{
		String qVal=(String)setVal.get("keyword");
		String connUrl=(String)setVal.get("conn_url");
		String userAgent=(String)setVal.get("user_agent");
		String rsStr=null;
		HashMap<String, String> data = new HashMap<>();
		
        JsoupSSL.setSSL();
		
        //1시간 ==>파라메타lr:lang_1ko,qdr:h
        //1일 ==> qdr:d
        // 파라메타값 셋팅
		data.put("q", qVal);
		data.put("hl", "ko");
		data.put("tbm","nws");
		data.put("tbs", "qdr:d");
		data.put("biw","1920");
		
        //3. HTML GET으로 가져오기 
       Connection conn = Jsoup
                .connect(connUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .userAgent(userAgent)
                .data(data)
                .ignoreContentType(true);
        Document result = conn.get();
        
        Element getPart = result.getElementById("nav");
        Elements elems = getPart.select("a[href]");
        String hrefStr = elems.attr("abs:href");
        
		String cutStr = hrefStr.substring(hrefStr.indexOf("ei=")+3);
		rsStr = cutStr.substring(0,cutStr.indexOf("&"));
        
       
        
        
        return rsStr;
	}
	
	public static Document documentConnRequest(Map<String, Object> setVal) throws Exception{
		
		String qVal=(String)setVal.get("keyword");
		String connUrl=(String)setVal.get("conn_url");
		String userAgent=(String)setVal.get("user_agent");
		String getEi=(String)setVal.get("ei");
		
		//int i=0;
		HashMap<String, String> data = new HashMap<>();
		
		if(connUrl.indexOf("https://") >= 0){
            JsoupSSL.setSSL();
        }
		//logger.info("getEi==================>"+getEi);
		if(getEi!=null) {
			//int num=i++;
			//String pageNum=String.valueOf(num*10);
			data.put("q", qVal);
			data.put("hl", "ko");
			data.put("tbm","nws");
			data.put("tbs", "qdr:d");
			data.put("biw","1920");
			data.put("ei",getEi);
			//data.put("start",pageNum);
			
		}else{
			//1시간 ==>파라메타lr:lang_1ko,qdr:h
	        //1일 ==> qdr:d
	        // 파라메타값 셋팅
			
			data.put("q", qVal);
			data.put("hl", "ko");
			data.put("tbm","nws");
			data.put("tbs", "qdr:d");
			data.put("biw","1920");
		}
		//logger.info("dataValue==================>"+data.toString());
        //3. HTML GET으로 가져오기 
       Connection conn = Jsoup
                .connect(connUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .userAgent(userAgent)
                .data(data)
                .ignoreContentType(true);
        Document resultHtml = conn.get();

        return resultHtml;
		
	}
	
	public static int documentConnResponse(HashMap<String, Object> setVal) throws Exception{

		String connUrl=(String)setVal.get("connurl");
		String userAgent=(String)setVal.get("useragent");
		
		// 2. SSL 체크
        if(connUrl.indexOf("https://") >= 0){
            JsoupSSL.setSSL();
        }
        
        /*해당 사이트 존재 여부 체크 site check*/
        Connection.Response res = Jsoup
        						.connect(connUrl)
        						.header("Content-Type", "application/json;charset=UTF-8")
        						.userAgent(userAgent)
        						.ignoreContentType(true)
        						.execute();
        
        int chkVal = res.statusCode();
		
        return chkVal;
	}

}
