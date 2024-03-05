package com.example.backoffice.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String email;
    private String password;
    private String department;
    private String auth;
    public SignupRequestDto(String email, String password, String department, String auth) {
        this.email = email;
        this.password = password;
        this.department = department;
        this.auth = auth;
    }
}
