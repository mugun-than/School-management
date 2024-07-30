package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepo;
    private final CourseRepository courseRepo;

    public QuestionService(QuestionRepository questionRepo, CourseRepository courseRepo) {
        this.questionRepo = questionRepo;
        this.courseRepo = courseRepo;
    }

    public Question createQuestion(QuestionEntryDTO questionEntryDTO) {
        final Course course = this.courseRepo.findById(questionEntryDTO.getCourseId()).orElseThrow(NullPointerException::new);
        final Question question = Question.builder().question(questionEntryDTO.getQuestion()).option1(questionEntryDTO.getOption1()).option2(questionEntryDTO.getOption2()).ans(questionEntryDTO.getAns()).course(course).createdAt(Instant.now()).build();
        return this.questionRepo.save(question);
    }

    public List<Question> findByCourse(Long courseId) {
        final Course course = courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        return this.questionRepo.findAllByCourse(course);
    }

    public List<Question> findAll() {
        return this.questionRepo.findAll();
    }

    public Question findById(Long questionId) {
        return this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
    }

    public List<QuestionDTO> getQuestionForTestByCourse(Long courseId) {
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        final List<Question> result = this.questionRepo.findAllByCourse(course);
        final List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : result) {
            QuestionDTO questionDTO = QuestionDTO.builder().question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).id(question.getId()).build();
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Question updateQuestion(Long questionId, QuestionEntryDTO questionEntryDTO) {
        final Question temp = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(questionEntryDTO.getCourseId()).orElseThrow(NullPointerException::new);
        temp.setUpdatedAt(Instant.now());
        temp.setQuestion(questionEntryDTO.getQuestion());
        temp.setAns(questionEntryDTO.getAns());
        temp.setCourse(course);
        temp.setOption1(questionEntryDTO.getOption1());
        temp.setOption2(questionEntryDTO.getOption2());
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
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        this.questionRepo.deleteByCourse(course);
        return "Question of course: "+course.getName()+" deleted";
    }
}
