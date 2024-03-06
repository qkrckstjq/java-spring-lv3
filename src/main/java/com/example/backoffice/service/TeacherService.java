package com.example.backoffice.service;

import com.example.backoffice.model.dto.TeacherEditRequestDto;
import com.example.backoffice.model.dto.TeacherRequestDto;
import com.example.backoffice.model.entity.Member;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.repository.MemberRepository;
import com.example.backoffice.repository.TeacherRepository;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    public Optional<Teacher> edit(TeacherEditRequestDto requestDto, Long teacherId) {
        Optional<Teacher> existTeacher = teacherRepository.findById(teacherId);
        if(!existTeacher.isPresent()) {
            throw new IllegalArgumentException("존재 하지 않는 강사입니다.");
        }
        Teacher teacher = existTeacher.get();
        teacher.setTeahcer(requestDto);
        log.info("수정 완료");
        return Optional.of(teacherRepository.save(teacher));
    }

    public Optional<Teacher> findTeacher(Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(!teacher.isPresent()) {
            throw new IllegalArgumentException("존재하지 않는 강사입니다.");
        }
        return teacher;
    }
}
