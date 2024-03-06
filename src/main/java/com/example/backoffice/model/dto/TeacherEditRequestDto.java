package com.example.backoffice.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherEditRequestDto {
    @NotBlank
    private Long career;
    @NotBlank
    private String company;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String intro;
}
