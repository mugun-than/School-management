package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepo;

    public StudentAnswerService(StudentAnswerRepository studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }
}
