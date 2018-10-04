package com.spring.project.common.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessCrawling {
	/*로거 선언*/
	private static final Logger logger = LoggerFactory.getLogger(ProcessCrawling.class);
	
	
	/*google search logic*/
	public static void getGoogleWeb(Document doc, Map<String, Object> commandMap) throws Exception {
		/*jsoup 구조 	Document
		 * 			 Element
		 * 			  Node
		 * 
		 * jsoup node select로 가져 오는 방법
         * doc.select("a") : <a> 요소를 모두 선택합니다.
 		 * doc.select("#logo") : id="logo" 인 요소를 선택합니다.
		 * doc.select(".head") : class="head"인 요소들을 선택합니다.
		 * doc.select("[href]") : href 속성을 가진 요소들을 선택합니다.
 		 * doc.select("[width=500]") : width 속성의 값이 500인 모든 요소들을 선택합니다.
         * */
		//마지막에 담을 list
        ArrayList<Object> list = new ArrayList<>();
        
        //원본 html 담기
		Document html = doc;
		
		//decompose 분해 객체 생성  
		Element getPart = html.getElementById("ires");
        
		//가공 되는 html 페이지 영역 
		Elements _backup = getPart.getElementsByClass("g"); 
        
        //html 클론 생성 및 분해 시작
		Elements _decompose=null;
		_decompose = _backup.clone();
		//사용하지 안는 g태그 속의 table 태그를 삭제 한다.
		_decompose.select("table").remove();
        
        //연결 페이지 url 축출 시작
        //_cutPart 속성의 a태그의 내용을 축출 한다.
        Elements elems = _decompose.select("a[href]").not(".imx0m");
        
        logger.info("elems     ==========>"+"[[[[[[[[[elems]]]]]]]]]]]] \n "+elems);
        
        //축출된 결과값을 리스트에 담는다.
        for(int i=0;i<elems.size();i++) {
        		Map<Object, String> _rowdata = new HashMap<>();
        		String _title = elems.eq(i).text();
        		String _url = elems.eq(i).attr("abs:href");
        		String _list = _decompose.select(".st").eq(i).text();
        		
        		_rowdata.put("url", _url);
        		_rowdata.put("title", _title);
        		_rowdata.put("list", _list);
        		
        		list.add(i,_rowdata);
        		logger.info("_rowdata     ==========>"+"[[[[[[[[["+i+"]]]]]]]]]]]] \n "+_rowdata.toString());
        }
               
        
	}
}
