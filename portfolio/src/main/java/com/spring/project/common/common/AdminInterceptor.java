package com.spring.project.common.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @작성자   :YES
 * @생성날자  :2018. 9. 14.
 * @페케이지명 :com.spring.project.common.common
 * @클래스명   :AdminInterceptor
 * @태그명    :
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	/**
	* Logger for this class
	*/	
	private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String adminValue = (request.getParameter("admin") == null ? "":request.getParameter("admin"));
		
		if(adminValue.equals("1")) {
			request.getSession().setAttribute("ADMIN_ID", "admin");
			request.getSession().setAttribute("ADMIN_NAME", "관리자");
		}
		return true;
	}

}
