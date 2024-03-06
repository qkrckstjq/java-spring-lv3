package com.example.backoffice.controller;

import com.example.backoffice.model.dto.LectureRequestDto;
import com.example.backoffice.model.dto.LectureResponseDto;
import com.example.backoffice.model.dto.TeacherResponseDto;
import com.example.backoffice.model.entity.Lecture;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    @PostMapping
    public ResponseEntity<LectureResponseDto> registLecture(@RequestBody LectureRequestDto requestDto, @RequestParam Long teacher) {
        Optional<Lecture> registedLecture = lectureService.save(requestDto, teacher);
        if(registedLecture.isPresent()) {
            return new ResponseEntity<>(new LectureResponseDto(registedLecture.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<LectureResponseDto> editLecture(@RequestBody LectureRequestDto requestDto, @RequestParam Long lecture) {
        Optional<Lecture> editLecture = lectureService.edit(requestDto, lecture);
        if(editLecture.isPresent()) {
            return new ResponseEntity<>(new LectureResponseDto(editLecture.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/teachers")
    public ResponseEntity<List<LectureResponseDto>> getLectures(@RequestParam("teacher") Long teacher) {
        List<LectureResponseDto> lectures = lectureService.findAllTeacherId(teacher);
        if(!lectures.isEmpty()) {
            return new ResponseEntity<>(lectures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/categories")
    public ResponseEntity<List<LectureResponseDto>> getCateLectures(@RequestParam("cate") String cate) {
        List<LectureResponseDto> lectures = lectureService.findAllCategory(cate);
        if(!lectures.isEmpty()) {
            return new ResponseEntity<>(lectures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
