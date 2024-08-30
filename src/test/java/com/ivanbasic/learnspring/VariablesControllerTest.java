package com.ivanbasic.learnspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanbasic.learnspring.dto.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class VariablesControllerTest {
    public static final String RESULT_AS_STRING =
"""
VARIABLES:
PathVariable pathId=1
Query Variable (@RequestParam) page=2
Query Variable (@RequestParam) size=3
Head var (@RequestHeader) head1=100
Head var (@RequestHeader) head2=101
Body var (@RequestBody) greeting=Greeting[id=1111, content=John Smith]""";

    @Autowired
    private MockMvc mvc;

    @Test
    public void showVariables_WithoutBody_ShouldReturn4xx_Test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/variables/1?name=NAME"));
        resultActions = resultActions.andExpect(status().is4xxClientError());

    }

    @Test
    public void showVariables_WithAllVariables_ShouldReturnOk_Test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .get("/variables/1?page=2&size=3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1111, \"content\":\"John Smith\"}" )
                .header("head1", "100")
                .header("head2", "101")
        );
        resultActions = resultActions.andExpect(status().isOk());

        MvcResult mvcResult = resultActions.andReturn();
        String result =  mvcResult.getResponse().getContentAsString();
        assertEquals(result, RESULT_AS_STRING);


    }

}
