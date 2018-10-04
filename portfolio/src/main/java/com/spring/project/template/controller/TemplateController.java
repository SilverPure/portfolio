package com.spring.project.template.controller;

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
import com.spring.project.template.service.TemplateService;
 
/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.template.controller
 * @클래스명   :TemplateController
 * @태그명    :
 */
@Controller
public class TemplateController {
	/*log 사용을 위한 logger 선언 */
	private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
	//Logger log = Logger.getLogger(this.getClass());
	
	
	/*TemplateService 호출 */
	@Resource(name="templateService")
	private TemplateService templateService;
	
	/* view
	 * 입력을 위한 빈페이지 사용
	 * */
	
	@RequestMapping(value="/viewTemplateBoard.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewTemplateBoard(CommandMap commandMap) throws Exception {
		logger.debug("==================viewTemplateBoard==============");
		ModelAndView mv = new ModelAndView("/insertBoard");
		mv.setViewName("insertBoard");
		return mv;
	}
	
	/*list
	 * ModelAndView 리턴값및 정보를 담아서 리턴    
	 * */
	@RequestMapping(value = "/sqlTemplat.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView openTemplateBoardList(Map<String,Object> commandMap) throws Exception {
		/*spring ModelAndView 객체를 생성,리턴 되는 jsp경로를 셋팅 */
		ModelAndView mv = new ModelAndView("/boardList");

		/*TemplateService 에서 select되어서 넘어 와서 list에 넣음 */
		List<Map<String,Object>> list = templateService.selectBoardList(commandMap);
		mv.setViewName("boardList");
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	/*insert proc
	 * ModelAndView insert후  리스트 페이지로 이동 한다. !redirect:/sqlTemplat.do!
	 * */
	@RequestMapping(value="/insertTemplateBoard.do")
	public ModelAndView insertTemplateBoard(CommandMap commandMap) throws Exception {
		
		logger.info("입력시 JSP에 받은 Map값 ==============>"+commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("redirect:/sqlTemplat.do");
		templateService.insertBoard(commandMap.getMap());
		
		return mv;
	}
	/*detail*/
	@RequestMapping(value="/detailTemplateBoard.do")
	public ModelAndView openDetailTemplateBoard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/detailTemplateBoard");
		return mv;
	}
	
	/*selectDetail*/
	@RequestMapping(value="/selectDetailTemplateBoard.do")
	public ModelAndView selectDetailTemplateBoard(CommandMap commandMap) throws Exception {
		logger.info("selectDetailTemplateBoard.do Map값  ==============>"+commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("/insertBoard");
		//getMap() 단건(map)으로 가져 와야 되므로 getMap사용 
		Map<String, Object> map = templateService.selectBoardDetail(commandMap.getMap());
		logger.info("result__selectDetailMap값  ==============>"+map);
		mv.setViewName("insertBoard");
		mv.addObject("map", map);
		return mv;
	}
	
	/*update proc*/
	@RequestMapping(value="/updateTemplateBoard.do")
	public ModelAndView updateTemplateBoard(CommandMap commandMap ) throws Exception {
		
		logger.info("updateTemplateBoard.do Map값 ==============>"+commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("redirect:/sqlTemplat.do");
		templateService.updateBoard(commandMap.getMap());
		
		return mv;
	}
	/*delete*/
	@RequestMapping(value="/deleteTemplateBoard.do")
	public ModelAndView deleteTemplateBoard(CommandMap commandMap) throws Exception {
		logger.info("deleteTemplateBoard.do Map값 ==============>"+commandMap.getMap());
		ModelAndView mv = new ModelAndView("redirect:/sqlTemplat.do");
		templateService.deleteBoard(commandMap.getMap());
		return mv;
	}
	
}