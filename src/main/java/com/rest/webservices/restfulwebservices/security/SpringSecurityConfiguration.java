package com.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // here the whole authentication needs to happen

        //authenticate requests
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // if not authenticated, show web page
        http.httpBasic(withDefaults());

        // csrf -> post & put
        http.csrf().disable();

        return  http.build();
    }
}
