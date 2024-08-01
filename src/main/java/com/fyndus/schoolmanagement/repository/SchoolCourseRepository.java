package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolCourseRepository extends JpaRepository<SchoolCourse, Long> {

    void deleteBySchool(School school);

    Optional<List<SchoolCourse>> findAllBySchool(School school);
}
