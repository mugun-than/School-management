package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.SchoolCourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/course")
public class SchoolCourseController {

    private final SchoolCourseService schoolCourseService;

    public SchoolCourseController(SchoolCourseService schoolCourseService) {
        this.schoolCourseService = schoolCourseService;
    }
}
