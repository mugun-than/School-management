package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.TutorSalaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor/salary")
public class TutorSalaryController {

    private final TutorSalaryService tutorSalaryService;

    public TutorSalaryController(TutorSalaryService tutorSalaryService) {
        this.tutorSalaryService = tutorSalaryService;
    }
}
