package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.TutorCourseDTO;
import com.fyndus.schoolmanagement.entity.TutorCourse;
import com.fyndus.schoolmanagement.service.TutorCourseService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor/course")
public class TutorCourseController {

    private final TutorCourseService tutorCourseService;

    public TutorCourseController(TutorCourseService tutorCourseService) {
        this.tutorCourseService = tutorCourseService;
    }

    @PostMapping()
    public TutorCourse createTutorCourse(@RequestBody TutorCourseDTO tutorCourseDTO) {
        return this.tutorCourseService.createTutorCourse(tutorCourseDTO);
    }

    @GetMapping()
    public List<TutorCourse> findAll() {
        return this.tutorCourseService.findAll();
    }

    @GetMapping("/{tutorCourseId}")
    public TutorCourse findById(@PathVariable Long tutorCourseId) {
        return this.tutorCourseService.findById(tutorCourseId);
    }

    @GetMapping("/course/{courseId}")
    public List<TutorCourse> findByCourse(@PathVariable Long courseId) {
        return this.tutorCourseService.findByCourse(courseId);
    }

    @PutMapping("/{tutorCourseId}")
    public TutorCourse updateTutorCourse(@PathVariable Long tutorCourseId, @RequestBody TutorCourseDTO tutorCourseDTO) {
        return this.tutorCourseService.updateTutorCourse(tutorCourseId, tutorCourseDTO);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.tutorCourseService.deleteAll();
    }

    @DeleteMapping("/{tutorCourseId}")
    public String deleteById(@PathVariable Long tutorCourseId) {
        return this.tutorCourseService.deleteById(tutorCourseId);
    }

    @Transactional
    @DeleteMapping("/course/{courseId}")
    public String deleteByCourse(@PathVariable Long courseId) {
        return this.tutorCourseService.deleteByCourse(courseId);
    }

    @Transactional
    @DeleteMapping("/tutor/{tutorId}")
    public String deleteByTutor(@PathVariable Long tutorId) {
        return this.tutorCourseService.deleteByTutor(tutorId);
    }
}
