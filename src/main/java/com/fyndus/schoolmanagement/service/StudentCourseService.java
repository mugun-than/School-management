package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }
}
