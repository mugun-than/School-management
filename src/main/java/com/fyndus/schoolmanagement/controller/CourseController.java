package com.fyndus.schoolmanagement.controller;

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
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping()
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/{courseId}")
    public Course findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping()
    public String deleteAll(){
        return courseService.deleteAll();
    }

    @DeleteMapping("/{courseId}")
    public String deleteById(@PathVariable Long courseId) {
        return courseService.deleteById(courseId);
    }
}
