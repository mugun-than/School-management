package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.TutorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long> {
}
