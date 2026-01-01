package com.ivanbasic.learnspring.security.passwords;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

class PasswordEncoderComparisonTest {

    private static final String RAW_PASSWORD = "Before1t1sCrypted";

    @Test
    void encodeWithArgon2() {
        PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String hash = encoder.encode(RAW_PASSWORD);
        System.out.println("hash={argon2}" + hash);
        assert encoder.matches(RAW_PASSWORD, hash);
    }

    @Test
    void encodeWithPbkdf2() {
        PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5();
        String hash = encoder.encode(RAW_PASSWORD);
        System.out.println("hash={pbkdf2}" + hash);
        assert encoder.matches(RAW_PASSWORD, hash);
    }

    @Test
    void encodeWithSha256() {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String hash = encoder.encode(RAW_PASSWORD);
        System.out.println("hash={sha256}" + hash);
        assert encoder.matches(RAW_PASSWORD, hash);
    }

    @Test
    void encodeWithLdapSha() {
        PasswordEncoder encoder = new LdapShaPasswordEncoder();
        String hash = encoder.encode(RAW_PASSWORD);
        System.out.println("hash={ldap}" + hash);
        assert encoder.matches(RAW_PASSWORD, hash);
    }
}
