package com.ivanbasic.learnspring.controller.integration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserAuthenticationIntegrationTest {

    private static final String RAW_PASSWORD = "Before1t1sCrypted";
    @Autowired
    MockMvc mvc;

    @ParameterizedTest
    @ValueSource(strings = {
            "bcryptuser",
            "argon2user",
            "pbkdf2user",
            "sha256user",
            "ldapuser"
    })
    void userCanAuthenticateAndAccessSecuredEndpoint(String username) throws Exception {
        // obtain token
        MvcResult result = mvc.perform(post("/token")
                        .with(httpBasic(username, RAW_PASSWORD)))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        // access secured endpoint
        mvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + username));
    }
}
