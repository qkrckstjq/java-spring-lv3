package com.example.backoffice.model.entity;


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
