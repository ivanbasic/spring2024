package com.ivanbasic.learnspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles( "test" )
public class GreetingControllerITest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetingTest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/greeting?name=SPRING", String.class);

        //id is increment. If we don't know the order of the test methods and classes, id could be 1 or 2,3...
        //assertThat(response.getBody()).isEqualTo( "{\"id\":1,\"content\":\"Hello, SPRING!\"}");

        assert response.getBody() != null;
        assertTrue( response.getBody().contains( "Hello, SPRING!"));

    }

}
