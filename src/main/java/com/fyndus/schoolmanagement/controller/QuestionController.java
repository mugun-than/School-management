package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionEntryDTO questionEntryDTO) {
        return new ResponseEntity<>(this.questionService.createQuestion(questionEntryDTO), HttpStatus.OK);
    }

    @GetMapping("/test/schoolCourse/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> getQuestionForTestByCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.questionService.getQuestionForTestByCourse(schoolCourseId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.questionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long questionId) {
        return new ResponseEntity<>(this.questionService.findById(questionId), HttpStatus.OK);
    }

    @GetMapping("/school-course/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> findBySchoolCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.questionService.findBySchoolCourse(schoolCourseId), HttpStatus.OK);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionEntryDTO questionEntryDTO) {
        return new ResponseEntity<>(this.questionService.updateQuestion(questionId, questionEntryDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.questionService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long questionId) {
        return new ResponseEntity<>(this.questionService.deleteById(questionId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<ResponseDTO> deleteByCourse(@PathVariable Long courseId) {
        return new ResponseEntity<>(this.questionService.deleteByCourse(courseId), HttpStatus.OK);
    }
}
