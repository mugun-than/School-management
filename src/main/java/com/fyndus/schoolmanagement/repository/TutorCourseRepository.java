package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long> {

    List<TutorCourse> findByCourse(Course course);

    void deleteByCourse(Course course);

    void deleteByTutor(Tutor tutor);
}
