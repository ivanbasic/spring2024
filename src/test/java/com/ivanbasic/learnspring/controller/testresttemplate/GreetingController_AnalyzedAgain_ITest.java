package com.ivanbasic.learnspring.controller.testresttemplate;

import com.ivanbasic.learnspring.dto.Greeting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles( "test" )
public class GreetingController_AnalyzedAgain_ITest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetingTest_bodyAsString() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/greeting?name=SPRING", String.class);

        String body = response.getBody();
        HttpStatusCode status    = response.getStatusCode();
        HttpHeaders httpHeaders =response.getHeaders();


        //id is increment. If we don't know the order of the test methods and classes, id could be 1 or 2,3...
        //assertEquals( "{\"id\":1,\"content\":\"Hello, SPRING!\"}" , body  );
        assert body != null;
        assertTrue( body.contains( "Hello, SPRING!"));

        assertTrue(status.is2xxSuccessful());

        assertEquals( 5, httpHeaders.size() );
        assertTrue( httpHeaders.containsKey("Content-Type"));
        assertTrue( httpHeaders.containsKey("Transfer-Encoding"));
        assertTrue( httpHeaders.containsKey("Date"));
        assertTrue( httpHeaders.containsKey("Keep-Alive"));
        assertTrue( httpHeaders.containsKey("Connection"));
    }

    @Test
    public void greetingTest_BodyAsObject() throws Exception {
        ResponseEntity<Greeting> response = template.getForEntity("/greeting?name=SPRING", Greeting.class);

        Greeting body = response.getBody();
        HttpStatusCode status = response.getStatusCode();
        HttpHeaders httpHeaders = response.getHeaders();


        assert body != null;
        assertEquals("Hello, SPRING!", body.content());
        assertTrue( body.id() >0);

    }


}
