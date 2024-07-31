package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.StudentDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Student;
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
        final School school;
        final Student student = new Student();
        try {
            school = this.schoolRepo.findById(studentDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+studentDTO.getSchoolId()+" "+ ResponseMessage.NOT_FOUND).build();
        }
        student.setSchool(school);
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setCreatedAt(Instant.now());
        return ResponseDTO.builder().data(this.studentRepo.save(student)).message(ResponseMessage.CREATED).build();
    }

    public ResponseDTO findById(Long studentId) {
        final Student student;
        try {
            student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message("Student id: "+studentId+" "+ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder().data(student).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findAll() {
        final List<Student> students = this.studentRepo.findAll();
        if(students.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(students).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findBySchool(Long schoolId) {
        final School school;
        try {
            school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+schoolId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<Student> students = this.studentRepo.findAllBySchool(school);
        if(students.isEmpty()) {
            return ResponseDTO.builder().message("School name: "+school.getName()+" "+ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(students).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateStudent(Long studentId, StudentDTO studentDTO) {

        final School school;
        final Student temp;
        try {
            school = this.schoolRepo.findById(studentDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+studentDTO.getSchoolId()+" "+ResponseMessage.NOT_FOUND).build();
        }
        try {
            temp = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("Student id: "+studentId+" "+ResponseMessage.NOT_FOUND).build();
        }
        temp.setUpdatedAt(Instant.now());
        temp.setName(studentDTO.getName());
        temp.setAddress(studentDTO.getAddress());
        temp.setSchool(school);
        return ResponseDTO.builder().data(this.studentRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }

    public ResponseDTO deleteAll() {
        final List<Student> students = this.studentRepo.findAll();
        if(students.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        this.studentRepo.deleteAll();
        return ResponseDTO.builder().data(students).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteById(Long studentId) {
        final Student student;
        try {
            student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message("Student id: "+studentId+" "+ResponseMessage.NOT_FOUND).build();
        }
        this.studentRepo.deleteById(studentId);
        return ResponseDTO.builder().data(student).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteBySchool(Long schoolId) {

        final School school;
        try {
            school = schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        }catch (NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+schoolId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<Student> students = this.studentRepo.findAllBySchool(school);
        if(students.isEmpty()) {
            return ResponseDTO.builder().message("school: "+school.getName()+" "+ResponseMessage.EMPTY).build();
        }
        this.studentRepo.deleteAllBySchool(school);
        return ResponseDTO.builder().data(students).message(ResponseMessage.DELETED).build();
    }
}
