package com.example.backoffice.model.dto;


import com.example.backoffice.model.entity.Lecture;
import com.example.backoffice.model.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LectureResponseDto {
    private String title;
    private Long price;
    private String intro;
    private String category;
    private Teacher teacher;
    private Date createdAt;

    public LectureResponseDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.intro = lecture.getIntro();
        this.category = lecture.getCategory();
        this.teacher = lecture.getTeacher();
        this.createdAt = lecture.getCreatedAt();
    }
}
