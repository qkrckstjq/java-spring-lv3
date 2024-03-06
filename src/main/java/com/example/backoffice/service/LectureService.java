package com.example.backoffice.service;

import com.example.backoffice.model.dto.LectureRequestDto;
import com.example.backoffice.model.dto.LectureResponseDto;
import com.example.backoffice.model.entity.Lecture;
import com.example.backoffice.model.entity.Member;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.repository.LectureRepository;
import com.example.backoffice.repository.MemberRepository;
import com.example.backoffice.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    public LectureService(LectureRepository lectureRepository, TeacherRepository teacherRepository) {
        this.lectureRepository = lectureRepository;
        this.teacherRepository = teacherRepository;
    }

    public Optional<Lecture> save(LectureRequestDto requestDto, Long teacher) {
        Teacher existTeacher = teacherRepository.findById(teacher).orElseThrow(() ->
                new IllegalArgumentException("존재 하지 않는 강사 입니다.")
        );
        Lecture lecture = new Lecture(requestDto, existTeacher);
        return Optional.of(lectureRepository.save(lecture));
    }

    public Optional<Lecture> edit(LectureRequestDto requestDto, Long lecture) {
        Lecture existLecture = lectureRepository.findById(lecture).orElseThrow(() ->
                new IllegalArgumentException("존재 하지 않는 강의 영상 입니다.")
        );
        existLecture.setLecture(requestDto);
        return Optional.of(lectureRepository.save(existLecture));
    }

    public List<LectureResponseDto> findAllTeacherId(Long teacher) {
        return lectureRepository.findAllByTeacherId(teacher)
                .stream()
                .map(LectureResponseDto::new)
                .sorted(Comparator.comparing(LectureResponseDto::getCreatedAt))
                .toList();
    }

    public List<LectureResponseDto> findAllCategory(String cate) {
        return lectureRepository.findAllByCategory(cate)
                .stream()
                .map(LectureResponseDto::new)
                .sorted(Comparator.comparing(LectureResponseDto::getCreatedAt))
                .toList();
    }
}
