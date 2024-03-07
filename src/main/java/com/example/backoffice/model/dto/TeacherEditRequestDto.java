package com.example.backoffice.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherEditRequestDto {
    @NotNull
    @Positive
    private Long career;
    @NotBlank
    private String company;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String intro;
}
