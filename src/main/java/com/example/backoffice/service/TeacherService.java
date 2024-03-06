package com.example.backoffice.service;

import com.example.backoffice.model.dto.TeacherRequestDto;
import com.example.backoffice.model.entity.Member;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.repository.MemberRepository;
import com.example.backoffice.repository.TeacherRepository;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j(topic = "TeacherService log")
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final MemberRepository memberRepository;
    public TeacherService(TeacherRepository teacherRepository, MemberRepository memberRepository) {
        this.teacherRepository = teacherRepository;
        this.memberRepository = memberRepository;
    }

    public Optional<Teacher> save(TeacherRequestDto requestDto, Long memberId) {
        Optional<Teacher> existTeacher = teacherRepository.findByMemberId(memberId);
        if(existTeacher.isPresent()) {
            throw new IllegalArgumentException("이미 등록한 강사입니다.");
        }
        Member member = memberRepository.findById(memberId).get();
        Teacher teacher = new Teacher(requestDto);
        teacher.setMember(member);
        return Optional.of(teacherRepository.save(teacher));
    }
}
