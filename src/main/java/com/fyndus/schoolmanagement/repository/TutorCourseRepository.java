package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long> {

    void deleteByTutor(Tutor tutor);

    Optional<List<TutorCourse>> findAllBySchoolCourse(SchoolCourse schoolCourse);

    void deleteByAllSchoolCourse(SchoolCourse schoolCourse);
}
