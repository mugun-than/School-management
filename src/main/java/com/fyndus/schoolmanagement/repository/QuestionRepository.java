package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCourse(Course course);

    void deleteByCourse(Course course);
}
