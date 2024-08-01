package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.repository.StudentRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.time.Instant;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private SchoolRepository schoolRepo;

    public StudentService(StudentRepository studentRepo, SchoolRepository schoolRepo) {
        this.studentRepo = studentRepo;
        this.schoolRepo = schoolRepo;
    }

    public ResponseDTO createStudent(StudentDTO studentDTO) {
        final School school = this.schoolRepo.findById(studentDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        final Student student = new Student();
        student.setSchool(school);
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setCreatedAt(Instant.now());
        return ResponseDTO.builder()
                .data(this.studentRepo.save(student))
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findById(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder()
                .data(student)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findAll() {
        final List<Student> students = this.studentRepo.findAll();
        if(students.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder()
                .data(students)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<Student> students = this.studentRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder().data(students).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateStudent(Long studentId, StudentDTO studentDTO) {

        final School school = this.schoolRepo.findById(studentDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        student.setUpdatedAt(Instant.now());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setSchool(school);
        return ResponseDTO.builder()
                .data(this.studentRepo.save(student))
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<Student> students = this.studentRepo.findAll();
        if(students.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.studentRepo.deleteAll();
        return ResponseDTO.builder()
                .data(students)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteById(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        this.studentRepo.deleteById(studentId);
        return ResponseDTO.builder()
                .data(student)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteBySchool(Long schoolId) {

        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<Student> students = this.studentRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        this.studentRepo.deleteAllBySchool(school);
        return ResponseDTO.builder()
                .data(students)
                .message(ResponseMessage.DELETED)
                .build();
    }
}
