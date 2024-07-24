package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }
}
