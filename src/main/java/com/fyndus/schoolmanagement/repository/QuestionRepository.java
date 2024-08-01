package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<List<Question>> findAllBySchoolCourse(SchoolCourse schoolCourse);

    void deleteAllByCourse(SchoolCourse schoolCourse);
}
