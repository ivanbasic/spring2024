package com.ivanbasic.learnspring.controller.mockmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles( "test" )
@MockBean(SecurityFilterChain.class)
public class ActuatorResponseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void healthEndpointShouldReturn200() throws Exception {
        mockMvc.perform(get("/manage/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("UP")));
    }


    @Test
    public void metricsEndpointShouldReturn200() throws Exception {
        mockMvc.perform(get("/manage/metrics/jvm.memory.used"))
                .andExpect(status().isOk())
                 .andExpect(content().string(org.hamcrest.Matchers.containsString("jvm.memory.used")));
    }
}