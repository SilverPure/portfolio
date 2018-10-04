package com.spring.project.common.logger;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.common.logger
 * @클래스명   :FirstFilter
 * @태그명    :
 */
public class FirstFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(FirstFilter.class);
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("======================================          Init Call         ======================================");
		
		this.encoding = filterConfig.getInitParameter("encoding");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(this.encoding);
		
		// 요청과 응답에 필요한 처리를 수행할 Wrapper를 생성합니다.
         ServletRequest requestWrapper = new TestRequestWrapper((HttpServletRequest) request);
         ServletResponse responseWrapper = new TestResponseWrapper((HttpServletResponse) response);
        
        logger.info("======================================          START         ======================================");
        logger.info("Filter Request IP \t:"+request.getLocalAddr());
        logger.info("Filter Request CharacterEncoding \t:"+request.getCharacterEncoding());
        logger.info("Filter Request LocalName \t:"+request.getLocalName());
        logger.info("Filter Request Protocol \t:"+request.getProtocol());
        logger.info("======================================          END         ======================================");

     // 다음 필터의 호출, 실제 자원의 처리를 합니다.
        chain.doFilter(request,response);
        
     // 응답에 대한 처리를 하는곳 입니다.
        
     // 응답 래퍼를 이용하여 출력을 모두 대문자로 변형합니다.
     // response.getWriter().write(responseWrapper.toString().toUpperCase());
        /*if(response.isCommitted() == false) {
            response.getWriter().write(responseWrapper.toString());
        }*/
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("destroy call!!!!!!!!!!!!!!!!!!!");
		
	}
	
	/**
     * 요청 래퍼 입니다.
     */
    class TestRequestWrapper extends HttpServletRequestWrapper {

        public TestRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * 입력 파라미터에서 <, > 를 제거 합니다.
         */
        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            if(value == null) value = "";
            return value.replaceAll("[<>]", "");
        }
    }
    
    /**
     * 응답 래퍼 입니다.
     */
    class TestResponseWrapper extends HttpServletResponseWrapper {

        protected CharArrayWriter charWriter;

        public TestResponseWrapper(HttpServletResponse response) {
            super(response);
            charWriter = new CharArrayWriter();
        }

        /**
         * 출력을 나중에 수정할 수 있도록 모아둡니다.
         */
        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(charWriter);
        }

        @Override
        public String toString() {
            return charWriter.toString();
        }
    }
}
