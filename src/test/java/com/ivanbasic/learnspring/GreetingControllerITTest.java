package com.ivanbasic.learnspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerITTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetingTest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/greeting?name=SPRING", String.class);
        assertThat(response.getBody()).isEqualTo( "{\"id\":1,\"content\":\"Hello, SPRING!\"}");
    }

}
