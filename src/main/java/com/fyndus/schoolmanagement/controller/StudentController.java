package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentDTO;
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

    @PostMapping()
    public ResponseDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return this.studentService.createStudent(studentDTO);
    }

    @GetMapping()
    public ResponseDTO findAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseDTO findById(@PathVariable Long studentId) {
        return this.studentService.findById(studentId);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseDTO findBySchool(@PathVariable Long schoolId) {
        return this.studentService.findBySchool(schoolId);
    }

    @PutMapping("/{studentId}")
    public ResponseDTO updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        return this.studentService.updateStudent(studentId, studentDTO);
    }

    @DeleteMapping()
    public ResponseDTO deleteAll() {
        return this.studentService.deleteAll();
    }

    @DeleteMapping("/{studentId}")
    public ResponseDTO deleteById(@PathVariable Long studentId) {
        return this.studentService.deleteById(studentId);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public ResponseDTO deleteBySchool(@PathVariable Long schoolId) {
        return this.studentService.deleteBySchool(schoolId);
    }
}
