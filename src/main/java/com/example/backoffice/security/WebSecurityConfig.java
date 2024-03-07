package com.example.backoffice.security;

//import com.example.backoffice.security.filter.JwtAuthenticationFilter;
//import com.example.backoffice.security.filter.JwtAuthorizationFilter;

import com.example.backoffice.model.ValidEnum.Auth;
import com.example.backoffice.security.filter.JwtAuthenticationFilter;
import com.example.backoffice.security.filter.JwtAuthorizationFilter;
import com.example.backoffice.security.user.MemberDetailServiceCustom;
import com.example.backoffice.security.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtUtils jwtUtils;//컴포넌트로 등록
    private final MemberDetailServiceCustom memberDetailServiceCustom;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(JwtUtils jwtUtils, MemberDetailServiceCustom memberDetailServiceCustom, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtils = jwtUtils;
        this.memberDetailServiceCustom = memberDetailServiceCustom;
        this.authenticationConfiguration = authenticationConfiguration;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtils);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtils, memberDetailServiceCustom);
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        //HttpSecurity 요청에 대해 여러가지 보안검증을 시도할 수 있는 객체
        //아래의 경우 csrf 무시
        //
        httpSecurity.csrf((csrf) -> csrf.disable()); //csrf 무시

        httpSecurity.sessionManagement((sessionManagement) -> {//api설계로 session관리 비활성화
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        httpSecurity.authorizeHttpRequests((request) -> {
            request
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/teacher**").hasAuthority(Auth.MANAGER.getAuth())
                    .requestMatchers("/teacher/**").hasAuthority(Auth.MANAGER.getAuth())
                    .requestMatchers("/lecture/**").hasAuthority(Auth.MANAGER.getAuth())
                    .requestMatchers("/lecture**").hasAuthority(Auth.MANAGER.getAuth())
                    .anyRequest().authenticated();
        });
        httpSecurity.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //순서대로 jwtAuthorizationFilter -> JwtAuthenticationFilter -> UsernamePasswordAuthenticationFilter
        return httpSecurity.build();
    }
}
