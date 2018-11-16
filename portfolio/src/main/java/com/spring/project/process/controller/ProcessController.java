package com.spring.project.process.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.common.common.CommandMap;
import com.spring.project.process.service.ProcessService;

import twitter4j.JSONArray;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 24.
 * @페케이지명 :com.spring.project.process.controller
 * @클래스명   :ProcessController
 * @태그명    :
 */
@Controller
public class ProcessController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);
	
	/*ProcessService 호출 */
	@Resource(name="processService")
	private ProcessService processService;
	
	//크롤링 수집 프로세스 사용 안함
	/*@RequestMapping(value="/createCrawling.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView createCrawling(CommandMap commandMap) throws Exception{
		logger.info("/createCrawling.do Map값  ==============>"+commandMap.getMap());
		
		ModelAndView mv = new ModelAndView();
		if(!commandMap.isEmpty()) {
			processService.createCrawling(commandMap.getMap());
		}
		
		mv.setViewName("collection");
		
		return mv;
	}*/
	@RequestMapping(value="/selectContent.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView selectDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/contentlist");
		
		List<Map<String, Object>> list = processService.selectDetail(commandMap.getMap());
		
		JSONArray jlist=new JSONArray(list.toArray());
		
		mv.addObject("list",jlist);
		mv.setViewName("contentlist");

		return mv;
	}
	
	//실시간 검색어 수집
	@RequestMapping(value="/selectRTS.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView selectRealTimeSearch()  throws Exception {
		ModelAndView mv = new ModelAndView();
		//Thread crawling = new Thread(target);
				
		processService.insertRealTimeSearch();
		mv.setViewName("redirect:/listRTS.do");
		return mv;
	}
	
	//실시간 검색어 리스트
	@RequestMapping(value="/listRTS.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rtsList(Map<String,Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> rtsn=processService.rtsn(commandMap);
		List<Map<String, Object>> rtsd=processService.rtsd(commandMap);
		List<Map<String, Object>> list=processService.selectRTSBoadList(commandMap);
		
		JSONArray jlist=new JSONArray(list.toArray());
		
		mv.addObject("rtsn",rtsn);
		mv.addObject("rtsd",rtsd);
		mv.addObject("list",jlist);
		mv.setViewName("rtslist");
		return mv;
	}
}
