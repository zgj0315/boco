package test.cn.com.boco.service;

import cn.com.boco.Application;
import cn.com.boco.service.MroService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * MroService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 24, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class MroServiceTest {
    @Autowired
    private MroService mro;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: xml2Json(String strXml)
     */
    @Test
    public void testXml2Json() throws Exception {
        mro.xml2Json("/Users/zhaogj/tmp/TD-LTE_MRO_HUAWEI_OMC_1112_29.xml", "/Users/zhaogj/tmp/TD-LTE_MRO_HUAWEI_OMC_1112_29.json");
    }


} 
