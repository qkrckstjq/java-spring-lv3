package com.example.backoffice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    private String title;
    private Long price;
    private String intro;
    private String category;
}
