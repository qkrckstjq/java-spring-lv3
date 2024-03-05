package com.example.backoffice.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Column(name = "teacher_id")
    private Teacher teacherId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "intro")
    private String intro;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    private Date createdAt;

}
