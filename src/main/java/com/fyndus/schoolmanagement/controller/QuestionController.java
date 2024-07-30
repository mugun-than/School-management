package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
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
    public Question createQuestion(@RequestBody QuestionEntryDTO questionEntryDTO) {
        return this.questionService.createQuestion(questionEntryDTO);
    }

    @GetMapping("/test/course/{courseId}")
    public List<QuestionDTO> getQuestionForTestByCourse(@PathVariable Long courseId) {
        return this.questionService.getQuestionForTestByCourse(courseId);
    }

    @GetMapping()
    public List<Question> findAll() {
        return this.questionService.findAll();
    }

    @GetMapping("/{questionId}")
    public Question findById(@PathVariable Long questionId) {
        return this.questionService.findById(questionId);
    }

    @GetMapping("/course/{courseId}")
    public List<Question> findByCourse(@PathVariable Long courseId) {
        return this.questionService.findByCourse(courseId);
    }

    @PutMapping("/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @RequestBody QuestionEntryDTO questionEntryDTO) {
        return this.questionService.updateQuestion(questionId, questionEntryDTO);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.questionService.deleteAll();
    }

    @DeleteMapping("/{questionId}")
    public String deleteById(@PathVariable Long questionId) {
        return this.questionService.deleteById(questionId);
    }

    @Transactional
    @DeleteMapping("/course/{courseId}")
    public String deleteByCourse(@PathVariable Long courseId) {
        return this.questionService.deleteByCourse(courseId);
    }
}
