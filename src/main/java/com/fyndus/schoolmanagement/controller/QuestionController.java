package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.service.QuestionService;
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
    public Question createQuestion(@RequestBody Question question) {
        return this.questionService.createQuestion(question);
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
    public Question updateQuestion(@PathVariable Long questionId, Question question) {
        return this.questionService.updateQuestion(questionId, question);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.questionService.deleteAll();
    }

    @DeleteMapping("/{questionId}")
    public String deleteById(@PathVariable Long questionId) {
        return this.questionService.deleteById(questionId);
    }

    @DeleteMapping("/course/{courseId}")
    public String deleteByCourse(@PathVariable Long courseId) {
        return this.questionService.deleteByCourse(courseId);
    }
}
