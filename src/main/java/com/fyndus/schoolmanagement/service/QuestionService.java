package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.QuestionRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
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

    public ResponseDTO createQuestion(QuestionEntryDTO questionEntryDTO) {
        final Course course;
        try {
            course = this.courseRepo.findById(questionEntryDTO.getCourseId()).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Course id: "+questionEntryDTO.getCourseId()+" "+ResponseMessage.NOT_FOUND).build();
        }

        final Question question = Question.builder().question(questionEntryDTO.getQuestion()).option1(questionEntryDTO.getOption1()).option2(questionEntryDTO.getOption2()).ans(questionEntryDTO.getAns()).course(course).createdAt(Instant.now()).build();
        return ResponseDTO.builder().data(this.questionRepo.save(question)).message(ResponseMessage.CREATED).build();
    }

    public ResponseDTO findByCourse(Long courseId) {
        final Course course;
        try {
            course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Course id: "+courseId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<Question> questions = this.questionRepo.findAllByCourse(course);
        if(questions.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(questions).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findAll() {
        final List<Question> questions = this.questionRepo.findAll();
        if(questions.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(questions).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findById(Long questionId) {
        final Question question;
        try {
            question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder().data(question).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO getQuestionForTestByCourse(Long courseId) {
        final Course course;
        try {
            course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e){
            return ResponseDTO.builder().message("Course: "+courseId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<Question> result = this.questionRepo.findAllByCourse(course);
        if(result.isEmpty()) {
            return ResponseDTO.builder().message("Course: "+courseId+" "+ResponseMessage.EMPTY).build();
        }
        final List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : result) {
            QuestionDTO questionDTO = QuestionDTO.builder().question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).id(question.getId()).build();
            questionDTOList.add(questionDTO);
        }
        return ResponseDTO.builder().data(questionDTOList).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateQuestion(Long questionId, QuestionEntryDTO questionEntryDTO) {
        final Course course;
        try {
            course = this.courseRepo.findById(questionEntryDTO.getCourseId()).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Course id: "+questionEntryDTO.getCourseId()+" "+ResponseMessage.NOT_FOUND).build();
        }
        final Question temp;
        try {
            temp = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Question id: "+questionId+" "+ResponseMessage.NOT_FOUND).build();
        }
        temp.setUpdatedAt(Instant.now());
        temp.setQuestion(questionEntryDTO.getQuestion());
        temp.setAns(questionEntryDTO.getAns());
        temp.setCourse(course);
        temp.setOption1(questionEntryDTO.getOption1());
        temp.setOption2(questionEntryDTO.getOption2());
        return ResponseDTO.builder().data(this.questionRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }

    public ResponseDTO deleteAll() {
        final List<Question> questions = this.questionRepo.findAll();
        if(questions.isEmpty()) {
            return  ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        this.questionRepo.deleteAll();
        return ResponseDTO.builder().data(questions).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteById(Long questionId) {
        final Question question;
        try {
            question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Question id : "+questionId+" "+ResponseMessage.NOT_FOUND).build();
        }
        this.questionRepo.deleteById(questionId);
        return ResponseDTO.builder().data(question).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteByCourse(Long courseId) {
        final Course course;
        try {
            course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        }catch(NullPointerException e) {
            return ResponseDTO.builder().message("Course id: "+courseId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<Question> questions = this.questionRepo.findAllByCourse(course);
        if(questions.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        this.questionRepo.deleteAllByCourse(course);
        return ResponseDTO.builder().data(questions).message(ResponseMessage.DELETED).build();
    }
}
