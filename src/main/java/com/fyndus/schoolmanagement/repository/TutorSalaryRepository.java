package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorSalaryRepository extends JpaRepository<TutorSalary, Long> {
    List<TutorSalary> findByTutor(Tutor tutor);

    void deleteByTutor(Tutor tutor);
}
