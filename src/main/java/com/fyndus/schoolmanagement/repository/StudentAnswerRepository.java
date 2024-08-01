package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    Optional<List<StudentAnswer>> findAllByStudent(Student student);

    Optional<List<StudentAnswer>> findAllByQuestion_SchoolCourse(SchoolCourse schoolCourse);

    Optional<StudentAnswer> findByStudentAndQuestion(Student student, Question question);

    void deleteAllByStudent(Student student);

    void deleteAllByQuestion_SchoolCourse(SchoolCourse schoolCourse);

    void deleteAllByStudentAndQuestion_SchoolCourse(Student student, SchoolCourse schoolCourse);

    Optional<List<StudentAnswer>> findAllByStudentAndQuestion_SchoolCourse(Student student, SchoolCourse schoolCourse);
}
