package com.example.backoffice.repository;

import com.example.backoffice.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByMemberId(Long memberId);
}
