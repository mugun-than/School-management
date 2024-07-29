package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.service.StudentService;
import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final SchoolRepository schoolRepo;

    public StudentController(StudentService studentService, SchoolRepository schoolRepo) {
        this.studentService = studentService;
        this.schoolRepo = schoolRepo;
    }

    @PostMapping("/{schoolId}")
    public Student createStudent(@PathVariable   Long schoolId, @RequestBody Student student) {
        student.setSchool(schoolRepo.findById(schoolId).orElse(null));
        return this.studentService.createStudent(student);
    }

    @GetMapping()
    public List<Student> findAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findById(@PathVariable Long studentId) {
        return this.studentService.findById(studentId);
    }

    @GetMapping("/school/{schoolId}")
    public List<Student> findBySchool(@PathVariable Long schoolId) {
        return this.studentService.findBySchool(schoolId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        return this.studentService.updateStudent(studentId, student);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.studentService.deleteAll();
    }

    @DeleteMapping("/{studentId}")
    public String deleteById(@PathVariable Long studentId) {
        return this.studentService.deleteById(studentId);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public String deleteBySchool(@PathVariable Long schoolId) {
        return this.studentService.deleteBySchool(schoolId);
    }
}
