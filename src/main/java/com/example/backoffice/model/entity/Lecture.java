package com.example.backoffice.model.entity;


import com.example.backoffice.model.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "lecture")
@Getter
@Setter
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Long price;

    @Column(name = "intro")
    private String intro;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    private Date createdAt;

    public Lecture(LectureRequestDto requestDto, Teacher teacher) {
        this.teacher = teacher;
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.intro = requestDto.getIntro();
        this.category = requestDto.getCategory();
        this.createdAt = new Date();
    }

    public void setLecture(LectureRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.intro = requestDto.getIntro();
        this.category = requestDto.getCategory();
    }

}
