package com.example.backoffice.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    public static final String HEADER = "Authorization";
    public static final String ID = "ID";
    public static final String AUTH_KEY = "auth";
    public static final String PART = "part";
    public static final Long EXPIRE = 100000000L;


    private final String customKey = "테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트";
    private final byte[] bytes = Base64.getEncoder().encode(customKey.getBytes());
    private final Key key = Keys.hmacShaKeyFor(bytes);
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //토큰 생성
    public String createJWT(String email, Long id, String part, String auth) {
        Date date = new Date();
        return "Bearer " + Jwts.builder()
                .setSubject(email)
                .claim(ID, id)
                .claim(AUTH_KEY, auth)
                .claim(PART, part)
                .setExpiration(new Date(date.getTime() + EXPIRE))
                .signWith(key, signatureAlgorithm)
                .compact();
    }
    //토큰 쿠키에 저장
    public void addJwtInHeader(String token, HttpServletResponse response) {
        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행

//            Cookie cookie = new Cookie(HEADER, token); // Name-Value
//            cookie.setPath("/");
//
//            response.addCookie(cookie);
            response.addHeader(HEADER, token);
        } catch (UnsupportedEncodingException e) {
            log.info("지원하지 않는 인코딩 방식");
        }
    }

    //토큰 검증
    public boolean authorizationJwt(String token) {
        try {
            String subStringToken = URLDecoder.decode(token, "UTF-8").substring(7);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(subStringToken);
            return true;
        } catch (Exception e) {
            log.info("토큰이 유효하지 않음");
        }
        return false;
    }

    public Claims getParsedToken(String token) {
        try {
            String subStringToken = URLDecoder.decode(token, "UTF-8").substring(7);
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(subStringToken).getBody();
        } catch (Exception e) {
        }
        return null;
    }

}
