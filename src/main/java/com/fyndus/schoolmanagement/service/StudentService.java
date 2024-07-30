package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.StudentDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

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

    public Student createStudent(Student student) {
        student.setCreatedAt(Instant.now());
        return this.studentRepo.save(student);
    }

    public Student findById(Long studentId) {
        return this.studentRepo.findById(studentId).orElse(null);
    }

    public List<Student> findAll() {
        return this.studentRepo.findAll();
    }

    public List<Student> findBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        return this.studentRepo.findBySchool(school);
    }

    public Student updateStudent(Long studentId, StudentDTO studentDTO) {
        final School school = this.schoolRepo.findById(studentDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        final Student temp = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        temp.setUpdatedAt(Instant.now());
        temp.setName(studentDTO.getName());
        temp.setAddress(studentDTO.getAddress());
        temp.setSchool(school);
        return this.studentRepo.save(temp);
    }

    public String deleteAll() {
        this.studentRepo.deleteAll();
        return "All students deleted";
    }

    public String deleteById(Long studentId) {
        this.studentRepo.deleteById(studentId);
        return "Student with id: "+studentId+" deleted";
    }

    public String deleteBySchool(Long schoolId) {
        final School school = schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        this.studentRepo.deleteBySchool(school);
        return " Students belonging to school: "+school.getName()+" deleted";
    }
}
