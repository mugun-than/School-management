package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentCourse;
import com.fyndus.schoolmanagement.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findByStudent(Student student);

    List<StudentCourse> findByCourse(Course course);

    void deleteByStudent(Student student);

    void deleteByCourse(Course course);

    void deleteByTutor(Tutor tutor);
}
