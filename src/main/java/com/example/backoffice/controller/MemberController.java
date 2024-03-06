package com.example.backoffice.controller;

import com.example.backoffice.model.dto.LoginRequestDto;
import com.example.backoffice.model.dto.SignupRequestDto;
import com.example.backoffice.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("signup")
    public ResponseEntity<Void> signup (@RequestBody @Valid SignupRequestDto requestDto) {
        try {
            log.info("회원 가입 시작");
            memberService.signin(requestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
