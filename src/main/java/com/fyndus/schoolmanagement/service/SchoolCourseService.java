package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolCourseService {

    private final SchoolCourseRepository schoolCourseRepo;

    public SchoolCourseService(SchoolCourseRepository schoolCourseRepo) {
        this.schoolCourseRepo = schoolCourseRepo;
    }
}
