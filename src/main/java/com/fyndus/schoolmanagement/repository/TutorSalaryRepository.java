package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.TutorSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorSalaryRepository extends JpaRepository<TutorSalary, Long> {
}
