package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorSalary;
import com.fyndus.schoolmanagement.service.TutorSalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor/salary")
public class TutorSalaryController {

    private final TutorSalaryService tutorSalaryService;

    public TutorSalaryController(TutorSalaryService tutorSalaryService) {
        this.tutorSalaryService = tutorSalaryService;
    }

    @PostMapping()
    public TutorSalary createTutorSalary(@RequestBody TutorSalary tutorSalary) {
        return this.tutorSalaryService.createTutorSalary(tutorSalary);
    }

    @GetMapping()
    public List<TutorSalary> findAll() {
        return this.tutorSalaryService.findAll();
    }

    @GetMapping("/{tutorSalaryId}")
    public TutorSalary findById(@PathVariable Long tutorSalaryId) {
        return this.tutorSalaryService.findById(tutorSalaryId);
    }

    @GetMapping("/tutor/{tutorId}")
    public List<TutorSalary> findByTutor(@PathVariable Long tutorId) {
        return this.tutorSalaryService.findByTutor(tutorId);
    }

    @PutMapping("/{tutorSalaryId}")
    public TutorSalary updateTutorSalary(@PathVariable Long tutorSalaryId, @RequestBody TutorSalary tutorSalary) {
        return this.tutorSalaryService.updateTutorSalary(tutorSalaryId, tutorSalary);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.tutorSalaryService.deleteAll();
    }

    @DeleteMapping("/{tutorSalaryId}")
    public String deleteById(@PathVariable Long tutorSalaryId) {
        return this.tutorSalaryService.deleteById(tutorSalaryId);
    }

    @DeleteMapping("/tutor/{tutorId}")
    public String deleteByTutor(@PathVariable Long tutorId) {
        return this.tutorSalaryService.deleteByTutor(tutorId);
    }
}
