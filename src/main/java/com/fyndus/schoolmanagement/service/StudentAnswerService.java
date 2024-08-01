package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentAnswerDTO;
import com.fyndus.schoolmanagement.entity.*;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.*;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepo;
    private final StudentRepository studentRepo;
    private final QuestionRepository questionRepo;
    private final SchoolCourseRepository schoolCourseRepo;

    public StudentAnswerService(QuestionRepository questionRepo, SchoolCourseRepository schoolCourseRepo, StudentAnswerRepository studentAnswerRepo, StudentRepository studentRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
        this.studentRepo = studentRepo;
        this.questionRepo = questionRepo;
        this.schoolCourseRepo = schoolCourseRepo;
    }

    public ResponseDTO getStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {
        final Student student = this.studentRepo.findById(studentAnswerDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final List<Long> questionIds = new ArrayList<>(studentAnswerDTO.getAnswers().keySet());
        final List<Question> questions = this.questionRepo.findAllById(questionIds);
        final List<StudentAnswer> studentAnswers = new ArrayList<>();
        final Map<Long, Question> questionsMap = new HashMap<>();

        for(Question question : questions) {
            questionsMap.put(question.getId(), question);
        }

        for(Map.Entry<Long, Long> answer : studentAnswerDTO.getAnswers().entrySet()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudent(student);
            studentAnswer.setQuestion(questionsMap.get(answer.getKey()));
            studentAnswer.setStudentAns(answer.getValue());
            studentAnswer.setCreatedAt(Instant.now());
            studentAnswers.add(this.studentAnswerRepo.save(studentAnswer));
        }

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findById(Long studentAnswerId) {
        final StudentAnswer studentAnswer = this.studentAnswerRepo.findById(studentAnswerId).orElseThrow(NullPointerException::new);

        return ResponseDTO.builder()
                .data(studentAnswer)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findAll() {
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAll();
        if(studentAnswers.isEmpty()) {
            throw new NoSuchElementFoundException();
        }

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.FOUND)
                .build();
    }

    // check for invalid schoolCourseId
    public ResponseDTO findBySchoolCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByQuestion_SchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudent(student).orElseThrow(NoSuchElementFoundException::new);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findByStudentAndSchoolCourse(Long studentId, Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);

        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudentAndQuestion_SchoolCourse(student, schoolCourse).orElseThrow(NoSuchElementFoundException::new);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findByStudentAndQuestion(Long studentId, Long questionId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final Question question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);

        final StudentAnswer studentAnswer = this.studentAnswerRepo.findByStudentAndQuestion(student, question).orElseThrow(NoSuchElementFoundException::new);

        return ResponseDTO.builder()
                .data(studentAnswer)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO updateStudentAnswerByStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {
        final Student student = this.studentRepo.findById(studentAnswerDTO.getStudentId()).orElseThrow(NullPointerException::new);
        Map.Entry<Long, Long> firstQuestion = studentAnswerDTO.getAnswers().entrySet().iterator().next();
        Long questionId = firstQuestion.getKey();
        final Question question = this.questionRepo.findById(studentAnswerDTO.getAnswers().get(questionId)).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudentAndQuestion_SchoolCourse(student, question.getSchoolCourse()).orElseThrow(NoSuchElementFoundException::new);

        for(StudentAnswer studentAnswer : studentAnswers) {
            studentAnswer.setUpdatedAt(Instant.now());
            studentAnswer.setStudentAns(studentAnswerDTO.getAnswers().get(studentAnswer.getQuestion().getId()));
            this.studentAnswerRepo.save(studentAnswer);
        }

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAll();
        if(studentAnswers.isEmpty()){
            throw new NoSuchElementFoundException();
        }

        this.studentAnswerRepo.deleteAll();

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteById(Long studentAnswerId) {
        final StudentAnswer studentAnswer = this.studentAnswerRepo.findById(studentAnswerId).orElseThrow(NullPointerException::new);

        this.studentAnswerRepo.deleteById(studentAnswerId);

        return ResponseDTO.builder()
                .data(studentAnswer)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudent(student).orElseThrow(NoSuchElementFoundException::new);

        this.studentAnswerRepo.deleteAllByStudent(student);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteBySchoolCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByQuestion_SchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);

        this.studentAnswerRepo.deleteAllByQuestion_SchoolCourse(schoolCourse);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteByStudentAndSchoolCourse(Long studentId, Long schoolCourseId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudentAndQuestion_SchoolCourse(student, schoolCourse).orElseThrow(NoSuchElementFoundException::new);

        this.studentAnswerRepo.deleteAllByStudentAndQuestion_SchoolCourse(student, schoolCourse);

        return ResponseDTO.builder()
                .data(studentAnswers)
                .message(ResponseMessage.DELETED)
                .build();
    }
}
