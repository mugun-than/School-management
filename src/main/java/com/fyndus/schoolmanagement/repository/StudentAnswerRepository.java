package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
}
