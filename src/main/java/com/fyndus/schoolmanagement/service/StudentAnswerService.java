package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.QuestionDTO;
import com.fyndus.schoolmanagement.DTO.StudentAnswerDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Question;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import com.fyndus.schoolmanagement.repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepo;

    public StudentAnswerService(StudentAnswerRepository studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    public void createStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswer.setCreatedAt(Instant.now());
        this.studentAnswerRepo.save(studentAnswer);
    }

    public String getStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {

        final Student student = Student.builder().id(studentAnswerDTO.getStudentId()).build();
        final Course course = Course.builder().id(studentAnswerDTO.getCourseId()).build();
        for(Map.Entry<Long, Long> answer : studentAnswerDTO.getAnswers().entrySet()) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudent(student);
            studentAnswer.setCourse(course);
            studentAnswer.setQuestion(Question.builder().id(answer.getKey()).build());
            studentAnswer.setStudentAns(answer.getValue());
            this.createStudentAnswer(studentAnswer);
        }
        return "Student: "+ student.getName()+" answer updated successfully";
    }

    public StudentAnswer findById(Long studentAnswerId) {
        return this.studentAnswerRepo.findById(studentAnswerId).orElse(null);
    }

    public List<StudentAnswer> findAll() {
        return this.studentAnswerRepo.findAll();
    }

    public List<StudentAnswer> findByCourse(Long courseId) {

        Course course = Course.builder().id(courseId).build();
        return this.studentAnswerRepo.findByCourse(course);
    }

    public List<StudentAnswer> findByStudent(Long studentId) {
        Student student = Student.builder().id(studentId).build();
        return this.studentAnswerRepo.findByStudent(student);
    }

    public List<StudentAnswer> findByStudentAndCourse(Long studentId, Long courseId) {
        Course course = Course.builder().id(courseId).build();
        Student student = Student.builder().id(studentId).build();
        return this.studentAnswerRepo.findAllByStudentAndCourse(student, course);
    }

    public StudentAnswer findByStudentAndQuestion(Long studentId, Long questionId) {
        Student student = Student.builder().id(studentId).build();
        Question question = Question.builder().id(questionId).build();
        return this.studentAnswerRepo.findByStudentAndQuestion(student, question);
    }

    public String updateStudentAnswerByStudentAnswerDTO(StudentAnswerDTO studentAnswerDTO) {
        Student student = Student.builder().id(studentAnswerDTO.getStudentId()).build();
        Course course = Course.builder().id(studentAnswerDTO.getCourseId()).build();
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

    public String deleteByStudent(Student student) {
        studentAnswerRepo.deleteByStudent(student);
        return "All studentAnswer record of student: "+student.getName()+" deleted";
    }

    public String deleteByCourse(Course course) {
        studentAnswerRepo.deleteByCourse(course);
        return "All studentAnswer record of course: "+course.getName()+" deleted";
    }

    public String deleteByStudentAndCourse(Student student, Course course) {
        studentAnswerRepo.deleteByStudentAndCourse(student, course);
        return "All StudentAnswer record of student: "+student.getName()+" in course: "+course.getName()+" deleted";
    }
}
