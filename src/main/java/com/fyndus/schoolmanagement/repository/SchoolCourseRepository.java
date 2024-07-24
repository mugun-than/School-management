package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.SchoolCourse;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolCourseRepository extends JpaRepository<SchoolCourse, Long> {
}
