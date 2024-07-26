package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.service.SchoolCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school-course")
public class SchoolCourseController {

    private final SchoolCourseService schoolCourseService;

    public SchoolCourseController(SchoolCourseService schoolCourseService) {
        this.schoolCourseService = schoolCourseService;
    }

    @PostMapping()
    public SchoolCourse createSchoolCourse(@RequestBody SchoolCourse schoolCourse) {
        return this.schoolCourseService.createSchoolCourse(schoolCourse);
    }

    @GetMapping()
    public List<SchoolCourse> findAll() {
        return this.schoolCourseService.findAll();
    }

    @GetMapping("/{schoolCourseId}")
    public SchoolCourse findById(@PathVariable Long schoolCourseId) {
        return this.schoolCourseService.findById(schoolCourseId);
    }

    @GetMapping("/school/{schoolId}")
    public List<SchoolCourse> findBySchool(@PathVariable Long schoolId) {
        return this.schoolCourseService.findBySchool(schoolId);
    }

    @PutMapping("/{schoolCourseId}")
    public SchoolCourse updateSchoolCourse(@PathVariable Long schoolCourseId, @RequestBody SchoolCourse schoolCourse) {
        return this.schoolCourseService.updateSchoolCourse(schoolCourseId, schoolCourse);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.schoolCourseService.deleteAll();
    }

    @DeleteMapping("/{schoolCourseId}")
    public String deleteById(@PathVariable Long schoolCourseId) {
        return this.schoolCourseService.deleteById(schoolCourseId);
    }

    @DeleteMapping("/school/{schoolId}")
    public String deleteBySchool(@PathVariable Long schoolId) {
        return this.schoolCourseService.deleteBySchool(schoolId);
    }
}
