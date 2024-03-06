package com.example.backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {//PasswordEncoder라는 인터페이스의 구현체 BCryptPasswordEncoder
        return new BCryptPasswordEncoder(); //BCrypt는 가장 많이 사용되는 hash함수
    }
}
