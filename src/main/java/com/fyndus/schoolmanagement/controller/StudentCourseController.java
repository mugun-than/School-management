package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentCourseDTO;
import com.fyndus.schoolmanagement.entity.StudentCourse;
import com.fyndus.schoolmanagement.service.StudentCourseService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO> createStudentCourse(@RequestBody StudentCourseDTO studentCourseDTO) {
        return new ResponseEntity<>(this.studentCourseService.createStudentCourse(studentCourseDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.studentCourseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{studentCourseId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long studentCourseId) {
        return new ResponseEntity<>(this.studentCourseService.findById(studentCourseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResponseDTO> findByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentCourseService.findByStudent(studentId), HttpStatus.OK);
    }

    @PutMapping("/{studentCourseId}")
    public ResponseEntity<ResponseDTO> updateStudentCourse(@PathVariable Long studentCourseId, @RequestBody StudentCourseDTO studentCourseDTO) {
        return new ResponseEntity<>(this.studentCourseService.updateStudentCourse(studentCourseId, studentCourseDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.studentCourseService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{studentCourseId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long studentCourseId) {
        return new ResponseEntity<>(this.studentCourseService.deleteById(studentCourseId), HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<ResponseDTO> deleteByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentCourseService.deleteByStudent(studentId), HttpStatus.OK);
    }
}
