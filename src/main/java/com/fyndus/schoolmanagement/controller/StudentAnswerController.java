package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.StudentAnswerDTO;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import com.fyndus.schoolmanagement.service.StudentAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-answer")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @GetMapping()
    public String getStudentAnswerDTO(@RequestBody StudentAnswerDTO studentAnswerDTO) {
        return this.studentAnswerService.getStudentAnswerDTO(studentAnswerDTO);
    }

    @GetMapping("/{studentAnswerId}")
    public StudentAnswer findById(@PathVariable Long studentAnswerId) {
        return this.studentAnswerService.findById(studentAnswerId);
    }

    @GetMapping()
    public List<StudentAnswer> findAll() {
        return this.studentAnswerService.findAll();
    }

    @GetMapping("/course/{courseId}")
    public List<StudentAnswer> findByCourse(@PathVariable Long courseId) {
        return this.studentAnswerService.findByCourse(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentAnswer> findByStudent(@PathVariable Long studentId) {
        return this.studentAnswerService.findByStudent(studentId);
    }

    @GetMapping("/student/course/{studentId}/{courseId}")
    public List<StudentAnswer> findByStudentAndCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return this.studentAnswerService.findByStudentAndCourse(studentId, courseId);
    }

    @GetMapping("/student/question/{studentId}/{questionId}")
    public StudentAnswer findByStudentAndQuestion(@PathVariable Long studentId, @PathVariable Long questionId) {
        return this.studentAnswerService.findByStudentAndQuestion(studentId, questionId);
    }
}
