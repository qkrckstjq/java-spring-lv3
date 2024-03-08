package com.example.backoffice.model.entity;

import com.example.backoffice.model.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "part")
    private String part;

    @Column(name = "auth")
    private String auth;
    public Member(SignupRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.part = requestDto.getPart().toString();
        this.auth = requestDto.getAuth().toString();
    }
    public Member(SignupRequestDto requestDto, String password) {
        this.email = requestDto.getEmail();
        this.password = password;
        this.part = requestDto.getPart().toString();
        this.auth = requestDto.getAuth().toString();
    }
}
