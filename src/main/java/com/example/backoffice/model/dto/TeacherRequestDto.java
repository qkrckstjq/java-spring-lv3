package com.example.backoffice.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {
    @NotBlank
    private String name;
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
