package com.example.backoffice.service;


import com.example.backoffice.model.dto.SignupRequestDto;
import com.example.backoffice.model.entity.Member;
import com.example.backoffice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signin(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        Optional<Member> existMember = userRepository.findByEmail(email);
        if(existMember.isPresent()) {

        }

    }
}
