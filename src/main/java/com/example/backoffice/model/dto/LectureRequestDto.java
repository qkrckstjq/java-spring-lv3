package com.example.backoffice.model.dto;

import com.example.backoffice.model.ValidEnum.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    @NotBlank
    private String title;
    @NotNull
    @Positive
    private Long price;
    @NotBlank
    private String intro;
    private Category category;
}
