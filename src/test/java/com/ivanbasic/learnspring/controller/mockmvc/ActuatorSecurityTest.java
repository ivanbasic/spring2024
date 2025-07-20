package com.ivanbasic.learnspring.controller.mockmvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ActuatorSecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Accessing /manage/health without authentication should return 200 OK")
    public void whenAccessingHealthWithoutAuth_thenReturns200() throws Exception {
        mvc.perform(get("/manage/health"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Accessing /manage/info without authentication should return 200 OK")
    public void whenAccessingInfoWithoutAuth_thenReturns200() throws Exception {
        mvc.perform(get("/manage/info"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("Accessing anything other as ../db  without authentication should return 401")
    public void menageHealthDb_should_return_401_without_auth() throws Exception {
        mvc.perform(get("/manage/health/db"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Accessing anything other as ../system  without authentication should return 401")
    public void menageHealthSystem_should_return_401_without_auth() throws Exception {
        mvc.perform(get("/manage/health/system"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("Accessing anything other as ../metrics  without authentication should return 401")
    public void metrics_should_return_401_without_auth() throws Exception {
        mvc.perform(get("/manage/metrics/jvm.memory.used"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Accessing /manage/metrics/jvm.memory.used with valid Basic Auth should return 200 OK")
    public void whenAccessingMetricsWithAuth_thenReturns200() throws Exception {
        MvcResult result = this.mvc.perform(post("/token")
                        .with(httpBasic("ivan", "i")))
                .andExpect(status().isOk())
                .andReturn();
        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/manage/metrics/jvm.memory.used")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
