package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepo;

    public QuestionService(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    public Question createQuestion(Question question) {
        question.setCreatedAt(Instant.now());
        return this.questionRepo.save(question);
    }

    public List<Question> findByCourse(Long courseId) {
        final Course course = Course.builder().id(courseId).build();
        return this.questionRepo.findAllByCourse(course);
    }

    public List<Question> findAll() {
        return this.questionRepo.findAll();
    }

    public Question findById(Long questionId) {
        return this.questionRepo.findById(questionId).orElse(null);
    }

    public List<QuestionDTO> getQuestionForTestByCourse(Course course) {
        final List<Question> result = this.questionRepo.findAllByCourse(course);
        final List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : result) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question.getQuestion());
            questionDTO.setOption2(question.getOption2());
            questionDTO.setOption1(question.getOption1());

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Question updateQuestion(Long questionId, Question question) {
        final Question temp = this.questionRepo.findById(questionId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setQuestion(question.getQuestion());
        temp.setAns(question.getAns());
        temp.setCourse(question.getCourse());
        temp.setOption1(question.getOption1());
        temp.setOption2(question.getOption2());
        return this.questionRepo.save(temp);
    }

    public String deleteAll() {
        this.questionRepo.deleteAll();
        return "All question deleted";
    }

    public String deleteById(Long questionId) {
        this.questionRepo.deleteById(questionId);
        return "Question with id: "+questionId+" deleted";
    }

    public String deleteByCourse(Long courseId) {
        final Course course = Course.builder().id(courseId).build();
        this.questionRepo.deleteByCourse(course);
        return "Question of course: "+course.getName()+" deleted";
    }
}
