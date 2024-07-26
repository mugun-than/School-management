package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
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
        final School school = School.builder().id(schoolId).build();
        return this.studentRepo.findBySchool(school);
    }

    public Student updateStudent(Long studentId, Student student) {
        final Student temp = this.studentRepo.findById(studentId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setName(student.getName());
        temp.setAddress(student.getAddress());
        temp.setSchool(student.getSchool());
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
        final School school = School.builder().id(schoolId).build();
        this.studentRepo.deleteBySchool(school);
        return " Students belonging to school: "+school.getName()+" deleted";
    }
}
