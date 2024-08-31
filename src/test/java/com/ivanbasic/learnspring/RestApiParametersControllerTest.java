package com.ivanbasic.learnspring;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiParametersControllerTest {

    public static final String RESULT_AS_STRING =
            """
                    REST API PARAMETERS:
                    path @PathVariable pathId=1
                    query @RequestParam page=2
                    query @RequestParam size=3
                    head @RequestHeader head1=100
                    head @RequestHeader head2=101
                    body @RequestBody greeting=Greeting[id=1111, content=John Smith]""";

    @Autowired
    private MockMvc mvc;

    @Test
    public void showParameters_WithoutAllParameters_ShouldReturn4xx_Test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/rest-api-parameters/1?name=NAME"));

        resultActions.andExpect(status().is4xxClientError());

        MvcResult mvcResult = resultActions.andReturn();
        assertEquals("Required request header 'head1' for method parameter type int is not present", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void showParameters_WithAllParameters_ShouldReturnOk_Test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .get("/rest-api-parameters/1?page=2&size=3")
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

    @Test
    public void showParameters_WithAllParametersTidiedUp_ShouldReturnOk_Test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .get("/rest-api-parameters/{pathId}", 1)
                .param("page", "2")
                .param("size", "3")
                .header("head1", "100")
                .header("head2", "101")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getGreetingAsString() )

        );
        resultActions = resultActions.andExpect(status().isOk());

        MvcResult mvcResult = resultActions.andReturn();
        String result =  mvcResult.getResponse().getContentAsString();
        assertEquals(result, RESULT_AS_STRING);
    }


    private String getGreetingAsString() throws JsonProcessingException {
        Greeting greeting = new Greeting(1111, "John Smith");
        ObjectMapper objectMapper = new ObjectMapper();
        String greetingAsString = objectMapper.writeValueAsString(greeting);
        return greetingAsString;
    }

}
