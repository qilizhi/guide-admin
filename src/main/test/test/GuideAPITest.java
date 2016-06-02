package test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mlx.guide.util.EasemobClientUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp,src/main/resources")
@ContextHierarchy({ @ContextConfiguration(locations = { "classpath:spring-mvc.xml,applicationContext.xml" }) })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuideAPITest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	// 根据具体要测试的controller 测试
	@Autowired
	private EasemobClientUtil easemobClientUtil;

	@Before
	public void setUp() {

		/**
		 * 只测试所有
		 */
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	// @Test
	// public void testSearch() throws Exception {
	//
	// ResultActions result =
	// mockMvc.perform(MockMvcRequestBuilders.get("/v1/guide/9"));
	// MvcResult mrs = result.andDo(MockMvcResultHandlers.print()).andReturn();
	// // 返回状态码
	// int resultStr = mrs.getResponse().getStatus();
	//
	// // 如果返回状态相等 就通过
	// Assert.assertEquals(200, resultStr);
	// }

	@Test
	public void testList() {
		String s = easemobClientUtil.getApi_host();
		System.out.println(s);
		/*
		 * ResultActions result =
		 * mockMvc.perform(MockMvcRequestBuilders.get("/v1/guide/list").param(
		 * "page", "1") .param("pageSize", "30")); MvcResult mrs =
		 * result.andDo(MockMvcResultHandlers.print()).andReturn();
		 */
		// 返回状态码
		/*
		 * int resultStr = mrs.getResponse().getStatus(); // 如果返回状态相等 就通过
		 * Assert.assertEquals(200, resultStr);
		 */
	}

}
