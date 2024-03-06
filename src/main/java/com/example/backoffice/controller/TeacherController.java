package com.example.backoffice.controller;

import com.example.backoffice.model.dto.TeacherRequestDto;
import com.example.backoffice.model.dto.TeacherResponseDto;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

//    @PostMapping
//    public ResponseEntity<TeacherResponseDto> registTeacher(TeacherRequestDto requestDto) {
//        Optional<Teacher> registedTeacher = teacherService.save(requestDto);
//        if(registedTeacher.isPresent()) {
//            return new ResponseEntity<>(new TeacherResponseDto(registedTeacher.get()), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
