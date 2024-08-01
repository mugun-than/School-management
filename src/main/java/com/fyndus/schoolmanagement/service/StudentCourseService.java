package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentCourseDTO;
import com.fyndus.schoolmanagement.entity.*;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.*;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;
    private final StudentRepository studentRepo;
    private final SchoolCourseRepository schoolCourseRepo;
    private final TutorRepository tutorRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo, StudentRepository studentRepo, TutorRepository tutorRepo, SchoolCourseRepository schoolCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
        this.studentRepo = studentRepo;
        this.tutorRepo = tutorRepo;
        this.schoolCourseRepo = schoolCourseRepo;
    }

    public ResponseDTO createStudentCourse(StudentCourseDTO studentCourseDTO) {
        final Student student = this.studentRepo.findById(studentCourseDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final Tutor tutor = this.tutorRepo.findById(studentCourseDTO.getTutorId()).orElseThrow(NullPointerException::new);
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(studentCourseDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);

        final StudentCourse studentCourse = new StudentCourse();

        studentCourse.setCreatedAt(Instant.now());
        studentCourse.setTutor(tutor);
        studentCourse.setStudent(student);
        studentCourse.setSchoolCourse(schoolCourse);
        return ResponseDTO.builder()
                .data(studentCourse)
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findAll() {
        final List<StudentCourse> studentCourses = this.studentCourseRepo.findAll();
        if(studentCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder()
                .data(studentCourses)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final List<StudentCourse> studentCourses = this.studentCourseRepo.findAllByStudent(student).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder()
                .data(studentCourses)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findById(Long studentCourseId) {
        final StudentCourse studentCourse = this.studentCourseRepo.findById(studentCourseId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder()
                .data(studentCourse)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO updateStudentCourse(Long studentCourseId, StudentCourseDTO studentCourseDTO) {
        final StudentCourse studentCourse = this.studentCourseRepo.findById(studentCourseId).orElseThrow(NullPointerException::new);
        final Student student = this.studentRepo.findById(studentCourseDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final Tutor tutor = this.tutorRepo.findById(studentCourseDTO.getTutorId()).orElseThrow(NullPointerException::new);
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(studentCourseDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);

        studentCourse.setUpdatedAt(Instant.now());
        studentCourse.setStudent(student);
        studentCourse.setTutor(tutor);
        studentCourse.setSchoolCourse(schoolCourse);
        return ResponseDTO.builder()
                .data(this.studentCourseRepo.save(studentCourse))
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteById(Long studentCourseId) {
        final StudentCourse studentCourse = this.studentCourseRepo.findById(studentCourseId).orElseThrow(NullPointerException::new);
        this.studentCourseRepo.deleteById(studentCourseId);
        return ResponseDTO.builder()
                .data(studentCourse)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<StudentCourse> studentCourses = this.studentCourseRepo.findAll();
        if(studentCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.studentCourseRepo.deleteAll();
        return ResponseDTO.builder()
                .data(studentCourses)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        final List<StudentCourse> studentCourses = this.studentCourseRepo.findAllByStudent(student).orElseThrow(NoSuchElementFoundException::new);
        this.studentCourseRepo.deleteAllByStudent(student);
        return ResponseDTO.builder()
                .data(studentCourses)
                .message(ResponseMessage.DELETED)
                .build();
    }
}
