package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.StudentCourseDTO;
import com.fyndus.schoolmanagement.entity.StudentCourse;
import com.fyndus.schoolmanagement.service.StudentCourseService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping()
    public StudentCourse createStudentCourse(@RequestBody StudentCourseDTO studentCourseDTO) {
        return this.studentCourseService.createStudentCourse(studentCourseDTO);
    }

    @GetMapping()
    public List<StudentCourse> findAll() {
        return this.studentCourseService.findAll();
    }

    @GetMapping("/{studentCourseId}")
    public StudentCourse findById(@PathVariable Long studentCourseId) {
        return this.studentCourseService.findById(studentCourseId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentCourse> findByStudent(@PathVariable Long studentId) {
        return this.studentCourseService.findByStudent(studentId);
    }

    @PutMapping("/{studentCourseId}")
    public StudentCourse updateStudentCourse(@PathVariable Long studentCourseId, @RequestBody StudentCourseDTO studentCourseDTO) {
        return this.studentCourseService.updateStudentCourse(studentCourseId, studentCourseDTO);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.studentCourseService.deleteAll();
    }

    @DeleteMapping("/{studentCourseId}")
    public String deleteById(@PathVariable Long studentCourseId) {
        return this.studentCourseService.deleteById(studentCourseId);
    }

    @DeleteMapping("/student/{studentId}")
    public String deleteByStudent(@PathVariable Long studentId) {
        return this.studentCourseService.deleteByStudent(studentId);
    }
}
