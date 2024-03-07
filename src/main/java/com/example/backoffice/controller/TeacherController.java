package com.example.backoffice.controller;

import com.example.backoffice.model.dto.TeacherEditRequestDto;
import com.example.backoffice.model.dto.TeacherRequestDto;
import com.example.backoffice.model.dto.TeacherResponseDto;
import com.example.backoffice.model.entity.Teacher;
import com.example.backoffice.security.user.MemberDetailCustom;
import com.example.backoffice.service.TeacherService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j(topic = "강사 컨트롤러")
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> registTeacher(@RequestBody @Valid TeacherRequestDto requestDto, @AuthenticationPrincipal MemberDetailCustom member) {
        Optional<Teacher> registedTeacher = teacherService.save(requestDto, member.getId());
        if(registedTeacher.isPresent()) {
            return new ResponseEntity<>(new TeacherResponseDto(registedTeacher.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<TeacherResponseDto> editTeacher(@RequestBody @Valid TeacherEditRequestDto requestDto, @RequestParam Long teacher) {
        Optional<Teacher> editedTeacher = teacherService.edit(requestDto, teacher);
        if(editedTeacher.isPresent()) {
            return new ResponseEntity<>(new TeacherResponseDto(editedTeacher.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<TeacherResponseDto> getTeacher(@RequestParam Long teacher) {
        Optional<Teacher> findTeacher = teacherService.findTeacher(teacher);
        if(findTeacher.isPresent()) {
            return new ResponseEntity<>(new TeacherResponseDto(findTeacher.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
