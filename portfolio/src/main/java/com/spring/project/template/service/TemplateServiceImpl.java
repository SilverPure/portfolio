package com.spring.project.template.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.spring.project.template.dao.TemplateDAO;

/**
 * @작성자   :YES
 * @생성날자  :2018. 8. 9.
 * @페케이지명 :com.spring.project.template.service
 * @클래스명   :TemplateServiceImpl
 * @태그명    :
 */
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@Resource(name="templateDAO")
	private TemplateDAO templateDAO;
	
	/*조회*/
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return templateDAO.selectBoardList(map); 
	}
	/*입력*/
	@Override
	public void insertBoard(Map<String, Object> map) throws Exception {
		/*idx max값*/
		Map<String, Object> getMax = templateDAO.selectMax(map);
		logger.info("getMax!!!!!!!!!!=====>  "+getMax.get("MAX"));
		map.put("max",getMax.get("MAX"));
		templateDAO.insertBoard(map);
	}
	
	/*상세*/
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		Map<String, Object> resultMap = templateDAO.selectBoardDetail(map);
		return resultMap;
	}
	
	/*수정*/
	@Override
	public void updateBoard(Map<String, Object> map) throws Exception{
		templateDAO.updateBoard(map);
	}

	/*삭제*/
	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception{
		templateDAO.deleteBoard(map);
	}
}