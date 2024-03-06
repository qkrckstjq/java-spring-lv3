package com.example.backoffice.model.dto;

import com.example.backoffice.model.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String name;
    private String career;
    private String company;
    private String phone_number;
    private String intro;

    public TeacherResponseDto(Teacher teacher) {
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phone_number = teacher.getPhoneNumber();
        this.intro = teacher.getIntro();
    }

}
