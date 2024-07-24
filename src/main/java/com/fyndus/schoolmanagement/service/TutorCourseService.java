package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.TutorCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorCourseService {

    private final TutorCourseRepository tutorCourseRepo;

    public TutorCourseService(TutorCourseRepository tutorCourseRepo) {
        this.tutorCourseRepo = tutorCourseRepo;
    }
}
