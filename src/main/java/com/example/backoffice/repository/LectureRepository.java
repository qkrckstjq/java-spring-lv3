package com.example.backoffice.repository;

import com.example.backoffice.model.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTeacherId(Long teacherId);
    List<Lecture> findAllByCategory(String category);
}
