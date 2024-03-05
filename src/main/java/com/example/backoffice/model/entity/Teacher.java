package com.example.backoffice.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @OneToOne
    @Column(name = "member_id")
    private Member memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "career")
    private String career;

    @Column(name = "company")
    private String company;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "intro")
    private String intro;

}
