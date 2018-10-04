package com.spring.project.common.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
/* 스프링 사용 시,컨트롤러(Controller)에 들어오는 파라미터(Parameter)를 수정하거나 공통적으로 추가를 해주어야 하는 경우가 있다. 
 * 여러번 사용되는 값을 그렇게 일일히 세션에서 가져오는건 상당히 번거로운 코딩 방식 이므로 재활용성을 위해서 가져 오기 위한 클래스를 생성하여 준다.
 * 컨트롤러의 파라미터가 Map 형식이면 우리가 설정한 클래스가 아닌  스프링에서 기본적으로 설정된 ArgumentResolver를 거치게 된다. 
 * CommandMap 클래스는 추상 클래스 
 */
/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.common.common
 * @클래스명   :CommandMap
 * @태그명    :
 */
public class CommandMap {
Map<String,Object> map = new HashMap<String,Object>();
    
    public Object get(String key){
        return map.get(key);
    }
     
    public void put(String key, Object value){
        map.put(key, value);
    }
     
    public Object remove(String key){
        return map.remove(key);
    }
     
    public boolean containsKey(String key){
        return map.containsKey(key);
    }
     
    public boolean containsValue(Object value){
        return map.containsValue(value);
    }
     
    public void clear(){
        map.clear();
    }
     
    public Set<Entry<String, Object>> entrySet(){
        return map.entrySet();
    }
     
    public Set<String> keySet(){
        return map.keySet();
    }
     
    public boolean isEmpty(){
        return map.isEmpty();
    }
     
    public void putAll(Map<? extends String, ?extends Object> m){
        map.putAll(m);
    }
     
    public Map<String,Object> getMap(){
        return map;
    }
}
