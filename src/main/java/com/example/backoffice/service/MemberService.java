package com.example.backoffice.service;


import com.example.backoffice.model.dto.LoginRequestDto;
import com.example.backoffice.model.dto.SignupRequestDto;
import com.example.backoffice.model.entity.Member;
import com.example.backoffice.repository.MemberRepository;
import com.example.backoffice.security.PasswordConfig;
import com.example.backoffice.security.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j(topic = "멤버 서비스")
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    public MemberService(MemberRepository memberRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public void signin(SignupRequestDto requestDto) {
        log.info("이메일 중복 검사 시작");
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        Optional<Member> existMember = memberRepository.findByEmail(email);
        if(existMember.isPresent()) {
            log.info("이미 존재하는 이메일 입니다.");
           throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        log.info("가입 가능한 이메일 입니다.");
        memberRepository.save(new Member(requestDto, password));
    }
//    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
//        Member existMember = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(() ->
//            new IllegalArgumentException("존재 하지 않는 email 입니다.")
//        );
//        if(!existMember.getPassword().equals(requestDto.getPassword())) {
//            throw new IllegalArgumentException("비밀 번호가 일치하지 않습니다.");
//        }
//        String jwtToken = jwtUtils.createJWT(existMember.getEmail(), existMember.getId(), existMember.getPart(), existMember.getAuth());
//        jwtUtils.addJwtInHeader(jwtToken, res);
//    }
//
//    public void validJwt(String jwt) {
//        if(!jwtUtils.authorizationJwt(jwt)) {
//            throw new IllegalArgumentException("유효하지 않은 토큰");
//        }
//        log.info("유요한 토큰");
//    }
}
