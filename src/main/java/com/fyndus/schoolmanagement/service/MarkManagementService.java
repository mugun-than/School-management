package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.MarkManagement;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentAnswer;
import com.fyndus.schoolmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MarkManagementService {

    private final MarkManagementRepository markManagementRepo;
    private final StudentAnswerRepository studentAnswerRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public MarkManagementService(MarkManagementRepository markManagementRepo, StudentAnswerRepository studentAnswerRepo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.markManagementRepo = markManagementRepo;
        this.studentAnswerRepo = studentAnswerRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public void createMarkEntry(MarkManagement markManagement) {
        markManagement.setCreatedAt(Instant.now());
        this.markManagementRepo.save(markManagement);
    }

    public String calculateMarkForStudent(Long studentId, Long courseId) {

        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);

        MarkManagement markManagement = this.markManagementRepo.findByStudentAndCourse(student, course);
        if(markManagement != null) return "Student: "+markManagement.getStudent().getName()+" has scored a mark: "+markManagement.getMark();

        System.out.println("Entered past the if statement: !!!!!!!!!!!!!!!!!!!!");
        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudentAndCourse(student, course);
        int mark = 0;
        for(StudentAnswer answer : studentAnswers) {
            if(answer.getStudentAns() == answer.getQuestion().getAns()) mark++;
        }
        markManagement = new MarkManagement();
        markManagement.setMark(mark);
        markManagement.setStudent(student);
        markManagement.setCourse(course);
        this.createMarkEntry(markManagement);

        assert student != null;
        return "Student: "+student.getName()+" has scored a mark: "+mark+" in course: "+course.getName();
    }

    public String updateStudentMarkByCourse(Long studentId, Long courseId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final Course course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);

        final MarkManagement markManagement = this.markManagementRepo.findByStudentAndCourse(student, course);
        if(markManagement == null) return "Mark entry doesn't exists";

        final List<StudentAnswer> studentAnswers = this.studentAnswerRepo.findAllByStudentAndCourse(student, course);
        int mark = 0;
        for(StudentAnswer answer : studentAnswers) {
            if(answer.getStudentAns() == answer.getQuestion().getAns()) mark++;
        }
        markManagement.setMark(mark);
        markManagement.setUpdatedAt(Instant.now());
        this.markManagementRepo.save(markManagement);
        return "Student: "+student.getName()+" has been updated mark to: "+mark+" in course: "+course.getName();
    }

    public List<MarkManagement> findAll() {
        return this.markManagementRepo.findAll();
    }

    public String deleteAll() {
        this.markManagementRepo.deleteAll();
        return "All Marks deleted";
    }

    public String deleteById(Long markManagementId) {
        this.markManagementRepo.deleteById(markManagementId);
        return "Mark with id: "+markManagementId+" deleted";
    }
}
