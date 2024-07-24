package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.StudentAnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/answer")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }
}
