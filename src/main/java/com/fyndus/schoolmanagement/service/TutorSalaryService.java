package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.TutorSalaryRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorSalaryService {

    private final TutorSalaryRepository tutorSalaryRepo;

    public TutorSalaryService(TutorSalaryRepository tutorSalaryRepo) {
        this.tutorSalaryRepo = tutorSalaryRepo;
    }
}
