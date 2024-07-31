package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.service.QuestionService;
import jakarta.transaction.Transactional;
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
    public ResponseDTO createQuestion(@RequestBody QuestionEntryDTO questionEntryDTO) {
        return this.questionService.createQuestion(questionEntryDTO);
    }

    @GetMapping("/test/course/{courseId}")
    public ResponseDTO getQuestionForTestByCourse(@PathVariable Long courseId) {
        return this.questionService.getQuestionForTestByCourse(courseId);
    }

    @GetMapping()
    public ResponseDTO findAll() {
        return this.questionService.findAll();
    }

    @GetMapping("/{questionId}")
    public ResponseDTO findById(@PathVariable Long questionId) {
        return this.questionService.findById(questionId);
    }

    @GetMapping("/course/{courseId}")
    public ResponseDTO findByCourse(@PathVariable Long courseId) {
        return this.questionService.findByCourse(courseId);
    }

    @PutMapping("/{questionId}")
    public ResponseDTO updateQuestion(@PathVariable Long questionId, @RequestBody QuestionEntryDTO questionEntryDTO) {
        return this.questionService.updateQuestion(questionId, questionEntryDTO);
    }

    @DeleteMapping()
    public ResponseDTO deleteAll() {
        return this.questionService.deleteAll();
    }

    @DeleteMapping("/{questionId}")
    public ResponseDTO deleteById(@PathVariable Long questionId) {
        return this.questionService.deleteById(questionId);
    }

    @Transactional
    @DeleteMapping("/course/{courseId}")
    public ResponseDTO deleteByCourse(@PathVariable Long courseId) {
        return this.questionService.deleteByCourse(courseId);
    }
}
