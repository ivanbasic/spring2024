package com.ivanbasic.learnspring.context;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles( "test" )
class LearnSpringApplicationTests {

	@Test
	void contextLoads() {
	}

}