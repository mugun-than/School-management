package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseDTO createCourse(@RequestBody Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping()
    public ResponseDTO findAll() {
        return this.courseService.findAll();
    }

    @GetMapping("/{courseId}")
    public ResponseDTO findById(@PathVariable Long courseId) {
        return this.courseService.findById(courseId);
    }

    @PutMapping("/{courseId}")
    public ResponseDTO updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        return this.courseService.updateCourse(courseId, course);
    }

    @DeleteMapping()
    public ResponseDTO deleteAll(){
        return this.courseService.deleteAll();
    }

    @DeleteMapping("/{courseId}")
    public ResponseDTO deleteById(@PathVariable Long courseId) {
        return this.courseService.deleteById(courseId);
    }
}
