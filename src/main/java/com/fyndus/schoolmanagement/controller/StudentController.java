package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentDTO;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.service.StudentService;
import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(this.studentService.createStudent(studentDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentService.findById(studentId), HttpStatus.OK);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> findBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.studentService.findBySchool(schoolId), HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<ResponseDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(this.studentService.updateStudent(studentId, studentDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.studentService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentService.deleteById(studentId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> deleteBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.studentService.deleteBySchool(schoolId), HttpStatus.OK);
    }
}
