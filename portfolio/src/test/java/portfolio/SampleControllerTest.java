package portfolio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/config/action-servlet.xml"})
public class SampleControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);

	@Autowired // Dependency Injection(의존성 주입)
    private WebApplicationContext wac;
    private MockMvc mock;

    @Before
    public void beforeTest(){
        logger.info("===== beforeTest() =====");
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        logger.info("wac: " + wac);
        logger.info("mock: " + mock);
    }

    @Test
    public void doTest(){
        logger.info("===== doTest() =====");
        RequestBuilder req = MockMvcRequestBuilders.post("/login").
                param("userid","admin").
                param("pwd","!@#$");
        try {
            mock.perform(req);
            logger.info("수행 성공");
        } catch (Exception e) {
            logger.error("수행 실패: " + e.getMessage());
        }
    }

    /*@After
    public void afterTest(){
        logger.info("===== afterTest() =====");
    }*/

}