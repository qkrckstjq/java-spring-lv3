package com.example.backoffice.model.entity;


import com.example.backoffice.model.dto.TeacherEditRequestDto;
import com.example.backoffice.model.dto.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column(name = "name")
    private String name;

    @Column(name = "career")
    private Long career;

    @Column(name = "company")
    private String company;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "intro")
    private String intro;

    public Teacher(TeacherRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhone_number();
        this.intro = requestDto.getIntro();
    }

    public void setTeahcer(TeacherEditRequestDto requestDto) {
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhone_number();
        this.intro = requestDto.getIntro();
    }
}
