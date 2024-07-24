package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.TutorCourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor/course")
public class TutorCourseController {

    private final TutorCourseService tutorCourseService;

    public TutorCourseController(TutorCourseService tutorCourseService) {
        this.tutorCourseService = tutorCourseService;
    }
}
