package com.thinkgem.jeesite.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月10日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
"classpath:spring-context.xml",
"classpath:spring-context-activiti.xml",
"classpath:spring-context-jedis.xml",
"classpath:spring-context-shiro.xml",
"classpath:spring-mvc.xml"})
public class TestConfig {
}
