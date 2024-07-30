package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.StudentCourseDTO;
import com.fyndus.schoolmanagement.entity.*;
import com.fyndus.schoolmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;
    private final StudentRepository studentRepo;
    private final TutorCourseRepository tutorCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo, StudentRepository studentRepo, TutorCourseRepository tutorCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
        this.studentRepo = studentRepo;
        this.tutorCourseRepo = tutorCourseRepo;
    }

    public StudentCourse createStudentCourse(StudentCourseDTO studentCourseDTO) {
        final Student student = this.studentRepo.findById(studentCourseDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(studentCourseDTO.getTutorCourseId()).orElseThrow(NullPointerException::new);

        final StudentCourse studentCourse = new StudentCourse();

        studentCourse.setCreatedAt(Instant.now());
        studentCourse.setTutorCourse(tutorCourse);
        studentCourse.setStudent(student);
        return this.studentCourseRepo.save(studentCourse);
    }

    public List<StudentCourse> findAll() {
        return this.studentCourseRepo.findAll();
    }

    public List<StudentCourse> findByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        return this.studentCourseRepo.findByStudent(student);
    }

    public StudentCourse findById(Long studentCourseId) {
        return this.studentCourseRepo.findById(studentCourseId).orElseThrow(NullPointerException::new);
    }

    public StudentCourse updateStudentCourse(Long studentCourseId, StudentCourseDTO studentCourseDTO) {
        final StudentCourse temp = this.studentCourseRepo.findById(studentCourseId).orElseThrow(NullPointerException::new);
        final Student student = this.studentRepo.findById(studentCourseDTO.getStudentId()).orElseThrow(NullPointerException::new);
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(studentCourseDTO.getTutorCourseId()).orElseThrow(NullPointerException::new);
        temp.setUpdatedAt(Instant.now());
        temp.setStudent(student);
        temp.setTutorCourse(tutorCourse);
        return this.studentCourseRepo.save(temp);
    }

    public String deleteById(Long studentCourseId) {
        this.studentCourseRepo.deleteById(studentCourseId);
        return "StudentCourse with id: "+studentCourseId+" deleted";
    }

    public String deleteAll() {
        this.studentCourseRepo.deleteAll();
        return "All studentCourse deleted";
    }

    public String deleteByStudent(Long studentId) {
        final Student student = this.studentRepo.findById(studentId).orElseThrow(NullPointerException::new);
        this.studentCourseRepo.deleteByStudent(student);
        return "All studentCourse with student: "+student.getName()+" deleted";
    }
}
