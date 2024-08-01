package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchool(School school);

    Optional<List<Student>> findAllBySchool(School school);

    void deleteAllBySchool(School school);
}
