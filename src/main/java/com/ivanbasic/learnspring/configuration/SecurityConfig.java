package com.ivanbasic.learnspring.configuration;

import com.ivanbasic.learnspring.filter.AfterBasicAuthFilter;
import com.ivanbasic.learnspring.filter.AtRequestCacheFilter;
import com.ivanbasic.learnspring.filter.BeforeBasicAuthFilter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    private final RsaKeyProperties rsaKeys;
    public SecurityConfig(RsaKeyProperties rsaKeys) {
        LOG.info("SecurityConfig initialized");

        this.rsaKeys = rsaKeys;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        LOG.info("SecurityConfig.SecurityFilterChain created (JWT + HTTP Basic)");

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/manage/health", "/manage/info").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults())

                .addFilterBefore(new BeforeBasicAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AfterBasicAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AtRequestCacheFilter(), RequestCacheAwareFilter.class)

                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(
            @Qualifier("db3DataSource") DataSource dataSource) {

        LOG.info("SecurityConfig.UserDetailsService created with DB3 datasource");

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        LOG.info("SecurityConfig.PasswordEncoder created (default=bcrypt)");

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    JwtDecoder jwtDecoder() {
        LOG.info("SecurityConfig.JwtDecoder created");

        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        LOG.info("SecurityConfig.JwtEncoder created");

        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

}
