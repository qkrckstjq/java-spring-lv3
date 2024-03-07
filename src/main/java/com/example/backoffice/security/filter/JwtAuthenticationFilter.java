package com.example.backoffice.security.filter;


import com.example.backoffice.model.dto.LoginRequestDto;
import com.example.backoffice.security.user.MemberDetailCustom;
import com.example.backoffice.security.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Order(2)
@Slf4j(topic = "로그인 및 jwt토큰 발행")
//@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { //아이디, 비밀번호 로그인에 대한 인증을 시도하는 필터
    private final JwtUtils jwtUtils;
    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            log.info("email: "+requestDto.getEmail());
            log.info("password: "+requestDto.getPassword());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication result) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        MemberDetailCustom authenticationMember = (MemberDetailCustom) result.getPrincipal();
        String email = authenticationMember.getEmail();
        Long id = authenticationMember.getId();
        String part = authenticationMember.getPart();
        String auth = authenticationMember.getAuth();

        String token = jwtUtils.createJWT(email, id, part, auth);
        jwtUtils.addJwtInHeader(token, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
    }
}
