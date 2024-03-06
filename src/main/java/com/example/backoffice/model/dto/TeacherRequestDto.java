package com.example.backoffice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {
    @NotBlank
    private String name;
    @Size(min = 0, max = 1000)
    private Long career;
    @NotBlank
    private String company;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String intro;
}
