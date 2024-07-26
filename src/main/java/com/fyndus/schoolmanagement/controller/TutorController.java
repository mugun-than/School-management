package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.service.TutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping()
    public Tutor createTutor(Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @GetMapping()
    public List<Tutor> findAll() {
        return this.tutorService.findAll();
    }

    @GetMapping("/{tutorId}")
    public Tutor findById(@PathVariable Long tutorId) {
        return this.tutorService.findById(tutorId);
    }

    @GetMapping("/school/{schoolId}")
    public List<Tutor> findBySchool(@PathVariable Long schoolId) {
        return this.tutorService.findBySchool(schoolId);
    }

    @PutMapping("/{tutorId}")
    public Tutor updateTutor(@PathVariable Long tutorId, Tutor tutor) {
        return this.tutorService.updateTutor(tutorId, tutor);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.tutorService.deleteAll();
    }

    @DeleteMapping("/{tutorId}")
    public String deleteById(@PathVariable Long tutorId) {
        return this.tutorService.deleteById(tutorId);
    }

    @DeleteMapping("/school/{schoolId}")
    public String deleteBySchool(@PathVariable Long schoolId) {
        return this.tutorService.deleteBySchool(schoolId);
    }
}
