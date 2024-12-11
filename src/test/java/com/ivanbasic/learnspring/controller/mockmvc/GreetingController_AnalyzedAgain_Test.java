package com.ivanbasic.learnspring.controller.mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanbasic.learnspring.dto.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles( "test" )
@MockBean(SecurityFilterChain.class)
public class GreetingController_AnalyzedAgain_Test {

    @Autowired
    private MockMvc mvc;

    @Test
    public void greetingTest() throws Exception {


        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/greeting?name=SPRING"));


        resultActions = resultActions.andExpect(status().isOk());
        resultActions = resultActions.andExpect(content().string(containsString("Hello, SPRING!")));
        resultActions = resultActions.andDo(print());


        //get them as Greeting object
        MvcResult mvcResult = resultActions.andReturn();
        Greeting greeting =  new ObjectMapper().readValue(  mvcResult.getResponse().getContentAsString(), Greeting.class);
        assertNotNull(  greeting.content());
        assertTrue( greeting.id()>0 );

    }

}
