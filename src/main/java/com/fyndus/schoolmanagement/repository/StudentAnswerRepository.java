package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByCourse(Course course);

    List<StudentAnswer> findByStudent(Student student);

    List<StudentAnswer> findAllByStudentAndCourse(Student student, Course course);

    StudentAnswer findByStudentAndQuestion(Student student, Question question);

    void deleteByStudent(Student student);

    void deleteByCourse(Course course);

    void deleteByStudentAndCourse(Student student, Course course);
}
