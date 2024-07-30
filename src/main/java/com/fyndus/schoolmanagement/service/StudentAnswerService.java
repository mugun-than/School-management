package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.StudentAnswerDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.QuestionRepository;
import com.fyndus.schoolmanagement.repository.StudentAnswerRepository;
import com.fyndus.schoolmanagement.repository.StudentRepository;
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
    private final CourseRepository courseRepo;
    private final QuestionRepository questionRepo;

    public StudentAnswerService(QuestionRepository questionRepo, StudentAnswerRepository studentAnswerRepo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.questionRepo = questionRepo;
    }

    public void createStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswer.setCreatedAt(Instant.now());
        this.studentAnswerRepo.save(studentAnswer);
    }

    public String getStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {

        final Student student = this.studentRepo.findById(studentAnswerDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(studentAnswerDTO.getCourseId()).orElseThrow(NullPointerException::new);

        final List<Long> questionIds = new ArrayList<>(studentAnswerDTO.getAnswers().keySet());
        List<Question> questions = this.questionRepo.findAllById(questionIds);

        final Map<Long, Question> questionsMap = new HashMap<>();

        for(Question question : questions) {
            questionsMap.put(question.getId(), question);
        }

        for(Map.Entry<Long, Long> answer : studentAnswerDTO.getAnswers().entrySet()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudent(student);
            studentAnswer.setCourse(course);
            studentAnswer.setQuestion(questionsMap.get(answer.getKey()));
            studentAnswer.setStudentAns(answer.getValue());
            this.createStudentAnswer(studentAnswer);
        }
        return "Student: "+ student.getName()+" answer updated successfully";
    }

    public StudentAnswer findById(Long studentAnswerId) {
        return this.studentAnswerRepo.findById(studentAnswerId).orElseThrow(NullPointerException::new);
    }

    public List<StudentAnswer> findAll() {
        return this.studentAnswerRepo.findAll();
    }

    public List<StudentAnswer> findByCourse(Long courseId) {

        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        return this.studentAnswerRepo.findByCourse(course);
    }

    public List<StudentAnswer> findByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        return this.studentAnswerRepo.findByStudent(student);
    }

    public List<StudentAnswer> findByStudentAndCourse(Long studentId, Long courseId) {
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        return this.studentAnswerRepo.findAllByStudentAndCourse(student, course);
    }

    public StudentAnswer findByStudentAndQuestion(Long studentId, Long questionId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final Question question = this.questionRepo.findById(questionId).orElseThrow(NullPointerException::new);
        return this.studentAnswerRepo.findByStudentAndQuestion(student, question);
    }

    public String updateStudentAnswerByStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {
        final Student student = this.studentRepo.findById(studentAnswerDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(studentAnswerDTO.getCourseId()).orElseThrow(NullPointerException::new);
        List<StudentAnswer> result = studentAnswerRepo.findAllByStudentAndCourse(student, course);
        if(result.isEmpty()) return "Student entry doesn't exists";
        for(StudentAnswer answer : result) {
            answer.setUpdatedAt(Instant.now());
            answer.setStudentAns(studentAnswerDTO.getAnswers().get(answer.getQuestion().getId()));
            studentAnswerRepo.save(answer);
        }

        return "Answers for student: "+student.getName()+" of course: "+course.getName()+" updated";
    }

    public String deleteAll() {
        studentAnswerRepo.deleteAll();
        return "All studentAnswer records deleted";
    }

    public String deleteById(Long studentAnswerId) {
        studentAnswerRepo.deleteById(studentAnswerId);
        return "StudentAnswer record: "+studentAnswerId+" deleted";
    }

    public String deleteByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        studentAnswerRepo.deleteByStudent(student);
        return "All studentAnswer record of student: "+student.getName()+" deleted";
    }

    public String deleteByCourse(Long courseId) {
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        studentAnswerRepo.deleteByCourse(course);
        return "All studentAnswer record of course: "+course.getName()+" deleted";
    }

    public String deleteByStudentAndCourse(Long studentId, Long courseId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        studentAnswerRepo.deleteByStudentAndCourse(student, course);
        return "All StudentAnswer record of student: "+student.getName()+" in course: "+course.getName()+" deleted";
    }
}
