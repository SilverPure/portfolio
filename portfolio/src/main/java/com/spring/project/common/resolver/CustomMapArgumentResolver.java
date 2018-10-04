package com.spring.project.common.resolver;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.common.resolver
 * @클래스명   :CustomMapArgumentResolver
 * @태그명    :
 */
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.spring.project.common.common.CommandMap;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.common.resolver
 * @클래스명   :CustomMapArgumentResolver
 * @태그명    :
 */
/* supportsParameter 메서드는 Resolver가 적용 가능한지 검사하는 역할 
 * resolverArgument 메서드는 파라미터와 기타 정보를 받아서 실제 객체를 반환한다.
 * */
public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommandMap.class.isAssignableFrom(parameter.getParameterType());
    }
 
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CommandMap commandMap = new CommandMap();
         
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Enumeration<?> enumeration = request.getParameterNames();
         
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()){
            key = (String) enumeration.nextElement();
            values = request.getParameterValues(key);
            if(values != null){
                commandMap.put(key, (values.length > 1) ? values:values[0] );
            }
        }
        return commandMap;
    }
}
