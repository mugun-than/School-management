package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentCourse;
import com.fyndus.schoolmanagement.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    Optional<List<StudentCourse>> findAllByStudent(Student student);

    void deleteAllByStudent(Student student);
}
