package com.example.backoffice.model.dto;


import com.example.backoffice.model.ValidEnum.Auth;
import com.example.backoffice.model.ValidEnum.Part;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*#?&])[a-zA-Z0-9@$!%*#?&]{8,15}$")
    private String password;
    private Part part;
    private Auth auth;
}
