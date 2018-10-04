package com.spring.project.process.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.IsEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.project.common.jsoup.GooConnectionDom;
import com.spring.project.common.jsoup.JsoupSSL;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 31.
 * @페케이지명 :com.spring.project.process.service
 * @클래스명   :ProcessUtil
 * @태그명    :
 */

public class ProcessUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessUtil.class);
	
	/*사이트 존재 여부를 체크 함*/
	protected static int chkSite(String connUrl,String USER_AGENT) throws Exception {
		// 2. SSL 체크
        if(connUrl.indexOf("https://") >= 0){
            JsoupSSL.setSSL();
        }
        
        /*해당 사이트 존재 여부 체크 site check*/
        Connection.Response res = Jsoup
        						.connect(connUrl)
        						.header("Content-Type", "application/json;charset=UTF-8")
        						.userAgent(USER_AGENT)
        						.ignoreContentType(true)
        						.execute();
        
        logger.info("response===>"+res.statusCode()+" : "+res.statusMessage());
		
        int chkVal = res.statusCode();
		
        return chkVal;
	}
	
	/*process logic*/
	@Transactional
	public static List<Map<String, Object>> GoogleLogicProcess(HashMap<String, Object> setVal,String connUrl,String userAgent) throws Exception {
		
		String sCode=null;
		int rAnk=0;
		String sTime=null;
		String qVal=null;
		String rCode=null;
		Date cDate = new Date();
		Element getPart=null;
		
		SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm:ss");
		HashMap<String, String> data = new HashMap<>();
		//마지막에 담을 list
		List<Map<String, Object>> Rslist = new ArrayList<Map<String,Object>>();
		
		setVal.put("conn_url", connUrl);
		setVal.put("user_agent", userAgent);
		//사이트 크롤링 파라메타값 프로세스
		//String rsEi = GooConnectionDom.getParamEi(setVal);
		
		
		sCode=(String)setVal.get("site_code");
        rAnk=(int) setVal.get("rank");
        sTime=transFormat.format(setVal.get("stand_time"));
        qVal=(String)setVal.get("keyword");
        rCode=(String)setVal.get("row_code");
	        Document html= GooConnectionDom.documentConnRequest(setVal);
	        //decompose 분해 객체 생성
	  		getPart = html.getElementById("ires");
	  		
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
	          //축출된 결과값을 리스트에 담는다.
	          for(int i=0;i<elems.size();i++) {
	          		Map<String, Object> _rowdata = new HashMap<>();
	          		String _url = null;
	          		String _title = null;
	          		String _list = null;
	          		String _press = null;
	          		String _content = null;
	          		String cutUrl = null;
	          		
	          		_title = elems.eq(i).text();
	          		
	          		if(_title.isEmpty()) {
	          			_title="NONE"; 
	          		} 
	          		_url=elems.eq(i).attr("abs:href");
	          		
	          		//url주소에서 해당 언론사명 축출하기
	          		if(1==StringUtils.countOccurrencesOf(_url, ".")) {
	          			cutUrl = _url.substring(_url.indexOf("/")+2);
	          			_press = cutUrl.substring(0,cutUrl.indexOf("."));
	          			if(_url.isEmpty()) {
	              			continue;
	              		}
	          		}else{
	          			cutUrl = _url.substring(_url.indexOf(".")+1);
	          			_press =  cutUrl.substring(0,cutUrl.indexOf("."));
	          		}
	          		//같은 사이트 주소이면 예외 처리 함
	          		
	          		//구글 사이트에서 케쉬 정보가 조회 되어서  예외 처리 
	          		if("google".equals(_press)) {
	          			continue;
	          		}
	          		
	  				_list = _decompose.select(".st").eq(i).text();
	  				if(_list.isEmpty()) {
	          			continue;
	          		}
	  				//언로사별 실검 기사 조회 메소드
	  				_content = GetPressContent(_url,_press,userAgent);
	          		
	          		_rowdata.put("keyword", qVal);
	          		_rowdata.put("url", _url);
	          		_rowdata.put("title", _title);
	          		_rowdata.put("list", _list);
	          		_rowdata.put("press", _press);
	          		_rowdata.put("row_code", rCode);
	          		_rowdata.put("content", _content);
	          		_rowdata.put("rank", rAnk);
	          		_rowdata.put("stand_time", sTime);
	          		Rslist.add(_rowdata);
	          }
        
        return Rslist;
        
	}
	
	public static final String GetPressContent(String url,String press,String userAgent) throws Exception{
		
		String getContent = null;
		
		// 2. SSL 체크
        if(url.indexOf("https://") >= 0){
            JsoupSSL.setSSL();
        }
      
        Connection conn = Jsoup
                .connect(url)
                .header("Content-Type", "application/json;charset=UTF-8")
                .userAgent(userAgent)
                .ignoreContentType(true);
        Document html = conn.get();
		/*1.언론사별 기사 웹페이지를 분석 결과 80%의 언론사 웹페이지에서 속성 태그 itemprop를 사용 하고 있음
		 *2.getConten가 null일 경우 그외에 해당 null체크
		 *3.switch case를 사용 하여 해당 언론사별 웹페이지의 기사 본문이 있는 속성값을 지정 함
		 *4.더욱더 깔끔한 로직을 생각하여 로직 변경이 필요함!
		 * */
        getContent = html.getElementsByAttribute("itemprop").text();
        if(getContent.isEmpty()) {
        	switch (press) {
			case "huffingtonpost":
				getContent = html.getElementsByClass("content-list-component text").text();
				break;
			case "mt":
				getContent = html.getElementById("textBody").text();	
				break;
			case "donga":
				getContent = html.getElementsByClass("article_txt").text();
				break;
			case "ajunews":
				getContent = html.getElementById("articleBody").text();
				break;
			case "kookje":
				getContent = html.getElementById("news_textAres").text();
				break;
			case "lawissue":
				getContent = html.getElementById("CmAdContent").text();
				break;
			case "kbs":
				getContent = html.getElementById("cont_newstext").text();
				break;
			case "chosun":
				getContent = html.getElementsByClass("article").text();
				break;
			case "yonhapnews":
				getContent = html.getElementsByClass("article").text();
				break;
			case "hankookilbo":
				getContent = html.getElementsByClass("article-content").text();
				break;
			case "wikitree":
				getContent = html.getElementsByClass("article_head").text();
				break;
			case "polinews":
				getContent = html.getElementsByClass("smartOutput").text();
				break;
			case "iusm":
				getContent = html.getElementsByClass("cont-body").text();
				break;
			case "busan":
				getContent = html.getElementsByClass("articleBox").text();
				break;	
			default:
				getContent="EMPTY!!!!";
				break;
			}
        }
		logger.info("<========== getCont : "+url+" : "+press+" ===========>"+getContent);
        return getContent;
	}
	/*process logic*/
	@Transactional
	public static List<Map<String, Object>> DaumLogicProcess(HashMap<String, Object> setVal,String connUrl,String userAgent) throws Exception {
		
		String site=null;
		String sCode=null;
		int rAnk=0;
		String sTime=null;
		String qVal=null;
		String rCode=null;
		Date cDate = new Date();
		
		SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm:ss");
		HashMap<String, String> data = new HashMap<>();
		//마지막에 담을 list
		List<Map<String, Object>> Rslist = new ArrayList<Map<String,Object>>();
		
		setVal.put("conn_url", connUrl);
		setVal.put("user_agent", userAgent);
		
		sCode=(String)setVal.get("site_code");
        rAnk=(int)setVal.get("rank");
        sTime=transFormat.format(setVal.get("stand_time"));
        qVal=(String)setVal.get("keyword");
        rCode=(String)setVal.get("row_code");
       
        Document html= GooConnectionDom.documentConnRequest(setVal);

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

          //축출된 결과값을 리스트에 담는다.
          for(int i=0;i<elems.size();i++) {
          		Map<String, Object> _rowdata = new HashMap<>();
          		String _url = null;
          		String _title = null;
          		String _list = null;
          		String _press = null;
          		String _content = null;
          		String cutUrl = null;
          		
          		_title = elems.eq(i).text();
          		
          		if(_title.isEmpty()) {
          			_title="제목 생략"; 
          		} 
          		_url=elems.eq(i).attr("abs:href");
          		
          		if(1==StringUtils.countOccurrencesOf(_url, ".")) {
          			cutUrl = _url.substring(_url.indexOf("/")+2);
          			_press = cutUrl.substring(0,cutUrl.indexOf("."));
          			
          			if(_url.isEmpty()) {
              			continue;
              		}
          		}else{
          			cutUrl = _url.substring(_url.indexOf(".")+1);
          			_press =  cutUrl.substring(0,cutUrl.indexOf("."));
          		}
          		
  				_list = _decompose.select(".st").eq(i).text();
  				if(_list.isEmpty()) {
          			continue;
          		}
  				
          		if(_list.isEmpty()) {
          			_list = "내용 생략";
          		}
          		_content = GetPressContent(_url,_press,userAgent);
          		
          		_rowdata.put("keyword", qVal);
          		_rowdata.put("url", _url);
          		_rowdata.put("title", _title);
          		_rowdata.put("list", _list);
          		_rowdata.put("press", _press);
          		_rowdata.put("row_code", rCode);
          		_rowdata.put("content", _content);
          		_rowdata.put("rank", rAnk);
          		_rowdata.put("stand_time", sTime);
          		Rslist.add(_rowdata);
          }
          //logger.info("RslistDaum ==========>"+"[[[[[[[[]]]]]]]]]]] \n "+Rslist.toString());
        
        return Rslist;
        
	}
	
	
}
