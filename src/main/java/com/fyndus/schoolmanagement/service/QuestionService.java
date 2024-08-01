package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.QuestionEntryDTO;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.QuestionRepository;
import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepo;
    private final SchoolCourseRepository schoolCourseRepo;

    public QuestionService(QuestionRepository questionRepo, SchoolCourseRepository schoolCourseRepo) {
        this.questionRepo = questionRepo;
        this.schoolCourseRepo = schoolCourseRepo;
    }

    public ResponseDTO createQuestion(QuestionEntryDTO questionEntryDTO) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(questionEntryDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);
        final Question question = Question.builder()
                .question(questionEntryDTO.getQuestion())
                .option1(questionEntryDTO.getOption1())
                .option2(questionEntryDTO.getOption2())
                .ans(questionEntryDTO.getAns())
                .schoolCourse(schoolCourse).createdAt(Instant.now()).build();

        return ResponseDTO.builder()
                .data(this.questionRepo.save(question))
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findBySchoolCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<Question> questions = this.questionRepo.findAllBySchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder()
                .data(questions)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findAll() {
        final List<Question> questions = this.questionRepo.findAll();
        if(questions.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder()
                .data(questions)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findById(Long questionId) {
        final Question question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder()
                .data(question)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO getQuestionForTestByCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<Question> result = this.questionRepo.findAllBySchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);

        final List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : result) {
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .question(question.getQuestion())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .id(question.getId())
                    .build();

            questionDTOList.add(questionDTO);
        }
        return ResponseDTO.builder()
                .data(questionDTOList)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO updateQuestion(Long questionId, QuestionEntryDTO questionEntryDTO) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(questionEntryDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);
        final Question question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        question.setUpdatedAt(Instant.now());
        question.setQuestion(questionEntryDTO.getQuestion());
        question.setAns(questionEntryDTO.getAns());
        question.setSchoolCourse(schoolCourse);
        question.setOption1(questionEntryDTO.getOption1());
        question.setOption2(questionEntryDTO.getOption2());

        return ResponseDTO.builder()
                .data(this.questionRepo.save(question))
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<Question> questions = this.questionRepo.findAll();
        if(questions.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.questionRepo.deleteAll();
        return ResponseDTO.builder()
                .data(questions)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteById(Long questionId) {
        final Question question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);

        this.questionRepo.deleteById(questionId);
        return ResponseDTO.builder()
                .data(question)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteByCourse(Long courseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        final List<Question> questions = this.questionRepo.findAllBySchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);
        this.questionRepo.deleteAllByCourse(schoolCourse);

        return ResponseDTO.builder()
                .data(questions)
                .message(ResponseMessage.DELETED)
                .build();
    }
}
