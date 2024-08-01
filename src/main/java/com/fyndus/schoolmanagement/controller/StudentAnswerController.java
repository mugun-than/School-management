package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentAnswerDTO;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import com.fyndus.schoolmanagement.service.StudentAnswerService;
import jakarta.transaction.Transactional;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-answer")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> getStudentAnswer(@RequestBody StudentAnswerDTO studentAnswerDTO) {
        return new ResponseEntity<>(this.studentAnswerService.getStudentAnswerDTO(studentAnswerDTO), HttpStatus.OK);
    }

    @GetMapping("/{studentAnswerId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long studentAnswerId) {
        return new ResponseEntity<>(this.studentAnswerService.findById(studentAnswerId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.studentAnswerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/school-course/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> findBySchoolCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.studentAnswerService.findBySchoolCourse(schoolCourseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResponseDTO> findByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentAnswerService.findByStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/student/school-course/{studentId}/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> findByStudentAndSchoolCourse(@PathVariable Long studentId, @PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.studentAnswerService.findByStudentAndSchoolCourse(studentId, schoolCourseId), HttpStatus.OK);
    }

    @GetMapping("/student/question/{studentId}/{questionId}")
    public ResponseEntity<ResponseDTO> findByStudentAndQuestion(@PathVariable Long studentId, @PathVariable Long questionId) {
        return new ResponseEntity<>(this.studentAnswerService.findByStudentAndQuestion(studentId, questionId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> updateStudentAnswerByStudentAnswerDTO(@RequestBody StudentAnswerDTO studentAnswerDTO) {
        return new ResponseEntity<>(this.studentAnswerService.updateStudentAnswerByStudentAnswerDTO(studentAnswerDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.studentAnswerService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{studentAnswerId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long studentAnswerId) {
        return new ResponseEntity<>(this.studentAnswerService.deleteById(studentAnswerId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<ResponseDTO> deleteByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(this.studentAnswerService.deleteByStudent(studentId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/achool-ourse/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> deleteBySchoolCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.studentAnswerService.deleteBySchoolCourse(schoolCourseId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/student/school-course/{studentId}/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> deleteByStudentAndSchoolCourse(@PathVariable Long studentId, @PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.studentAnswerService.deleteByStudentAndSchoolCourse(studentId, schoolCourseId), HttpStatus.OK);
    }
}
