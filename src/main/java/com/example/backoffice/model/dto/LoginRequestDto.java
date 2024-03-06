package com.example.backoffice.model.dto;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
//    public LoginRequestDto(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
}
