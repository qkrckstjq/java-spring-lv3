package com.example.backoffice.security.filter;

import com.example.backoffice.security.user.MemberDetailServiceCustom;
import com.example.backoffice.security.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j(topic = "jwt토큰이 왔을때 토큰 검증")
//@Order(1)
public class JwtAuthorizationFilter extends OncePerRequestFilter { //jwt토큰에 대한 인증을 시도하는 필터
    //jwt토큰에 대해 인증을 시도하고 jwt토큰이 비어있다면 뒤 필터에게 넘어감
    private final JwtUtils jwtUtils;
    private final MemberDetailServiceCustom memberDetailServiceCustom;
    public JwtAuthorizationFilter(JwtUtils jwtUtils, MemberDetailServiceCustom memberDetailServiceCustom) {
        this.jwtUtils = jwtUtils;
        this.memberDetailServiceCustom = memberDetailServiceCustom;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            log.info("토큰 넘어옴 검사 시작" + token);
            if (jwtUtils.authorizationJwt(token)) {
                Claims memberInfo = jwtUtils.getParsedToken(token);

                // UserDetails 구현체
                UserDetails userDetails = memberDetailServiceCustom.loadUserByUsername(memberInfo.getSubject());

                // 토큰 인증을 통과한 유효한 사용자 객체 생성
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // 모든 필터가 공유할 SecurityContextHolder에 Authentication 객체 설정 다른 필터에서도 접근 할 수 있게
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("토큰 인증 완료 유효한 토큰");
            }
            return;
        }
        log.info("넘어온 토큰 없음 다음 필터로 넘어감");
        filterChain.doFilter(request, response);
    }
}
