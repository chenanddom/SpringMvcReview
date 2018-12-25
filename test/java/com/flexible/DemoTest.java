package com.flexible;

import com.flexible.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebmvcConfig.class})
@WebAppConfiguration(value = "src/main/resources")//1.
public class DemoTest {
    private MockMvc mockMvc;//2.

    @Autowired
    DemoService demoService;//3.

    @Autowired
    WebApplicationContext wac;//4.

    @Autowired
    MockHttpSession session;//5.

    @Autowired
    MockHttpServletRequest request;//6.

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testDemoController() throws Exception {
        mockMvc.perform(get("/getInfo"))
                .andExpect(status().isOk())
                .andExpect(content().string(demoService.getInfo()));
    }

}
