package com.ivanbasic.learnspring.security.passwords;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptPasswordTest {

    @Test
    void bcryptEncodingDemo() {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String raw = "Before1t1sCrypted";

        String hash1 = encoder.encode(raw);
        String hash2 = encoder.encode(raw);
        String hash3 = encoder.encode(raw);

        System.out.println(raw);
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash3);

        assertNotEquals(hash1, hash2);
        assertNotEquals(hash2, hash3);
        assertNotEquals(hash1, hash3);

        assertTrue(encoder.matches(raw, hash1));
        assertTrue(encoder.matches(raw, hash2));
        assertTrue(encoder.matches(raw, hash3));
    }
}