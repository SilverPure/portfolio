package com.spring.project.user.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.common.common.CommandMap;
import com.spring.project.user.service.LoginService;
	
/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 13.
 * @페케이지명 :com.spring.project.user.controller
 * @클래스명   :LoginController
 * @태그명    :
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	//로그인 
	@RequestMapping(value="/login.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView loginProcess(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception{
		logger.info("commandMap===>"+commandMap.getMap().toString());
		ModelAndView mv=new ModelAndView();
		if(!commandMap.isEmpty()) {
			
			loginService.loginProc(commandMap.getMap());
			Map<String, Object> userInfo=loginService.loginProc(commandMap.getMap());
			logger.info("result===>"+userInfo.toString());

			String userChk = userInfo.get("kakao_user").toString();

			if("Y".equals(userChk)) {
				String kakaoId=(String) userInfo.get("kakao_id").toString();
				String nickName=(String) userInfo.get("kakao_nick_name").toString();
				String userImg=(String) userInfo.get("kakao_profile_img").toString();
				session.setAttribute("userId", kakaoId);
				session.setAttribute("nickName", nickName);
				session.setAttribute("userImg", userImg);
			}else {
				String userId=(String) userInfo.get("user_id").toString();
				String nickName=(String) userInfo.get("nick_name").toString();
				session.setAttribute("userId", userId);
				session.setAttribute("nickName", nickName);
			}
			
			
			
			mv.addObject("map",userInfo);
			mv.setViewName("redirect:/listRTS.do");
		}else {
			mv.setViewName("login");
		}
					    
		return mv;
	}
	
	//로그인 등록 팝업
	@RequestMapping(value="/logout.do",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView loginPop(CommandMap commandMap, HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		
		session.invalidate();
		
		mv.setViewName("login");
		
		return mv;
	}
}
