package com.spring.project.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.common.logger
 * @클래스명   :FirstInceptor
 * @태그명    :
 */
public class FirstInceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(FirstInceptor.class);
	
	// 컨트롤러 실행 직전에 수행됩니다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	logger.info("======================================          INCEPTOR START         ======================================");

        if (handler instanceof HandlerMethod) {
            // HandlerMethod 는 후에 실행할 컨트롤러의 메소드 입니다.
            // 메소드명, 인스턴스, 타입, 사용된 Annotation 들을 확인할 수 있습니다.
            HandlerMethod method = (HandlerMethod) handler;
            
            logger.info("Inceptor handler Method name : " + method.getMethod().getName());
            logger.info("Inceptor handler Bean name : " + method.getBean().toString());
            logger.info("Inceptor handler Parameters name : " + method.getMethodParameters().toString());
            logger.info("Inceptor handler User Info : 아이디=" + request.getSession().getAttribute("userId")+",닉네임="+
            											       request.getSession().getAttribute("nickName")+",프로필사진="+
            											       request.getSession().getAttribute("userImg"));
            
        }
        logger.info("======================================          INCEPTOR END         ======================================");
        //session 체크
        /*HttpSession session=request.getSession(false);
        if(session != null) {
        	String chkSessionUser=(String) request.getSession().getAttribute("nickName");
	        if(chkSessionUser!=null) {
	        	return true;
	        }
        }
        response.sendRedirect(request.getContextPath()+"/login.do");
        return false;*/
        return true;
    }

    // 컨트롤러 실행 직후에 수행됩니다.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
                          throws Exception {
        logger.info("postHandle call......");
        
    }

    // View 렌더링이 끝난 직후에 수행됩니다.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                            throws Exception {
        logger.info("afterCompletion call......");
    }

    // 비동기 호출시 수행됩니다.
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
                              throws Exception {
        logger.info("afterConcurrentHandlingStarted call......");
    }
}
