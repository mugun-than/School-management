package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.Student;
import com.fyndus.schoolmanagement.entity.StudentCourse;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.repository.StudentCourseRepository;
import com.fyndus.schoolmanagement.repository.TutorCourseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }

    public StudentCourse createStudentCourse(StudentCourse studentCourse) {
        studentCourse.setCreatedAt(Instant.now());
        return this.studentCourseRepo.save(studentCourse);
    }

    public List<StudentCourse> findAll() {
        return this.studentCourseRepo.findAll();
    }

    public List<StudentCourse> findByStudent(Long studentId) {
        final Student student = Student.builder().id(studentId).build();
        return this.studentCourseRepo.findByStudent(student);
    }

    public List<StudentCourse> findByCourse(Long courseId) {
        final Course course = Course.builder().id(courseId).build();
        return this.studentCourseRepo.findByCourse(course);
    }

    public StudentCourse findById(Long studentCourseId) {
        return this.studentCourseRepo.findById(studentCourseId).orElse(null);
    }

    public StudentCourse updateStudentCourse(Long studentCourseId, StudentCourse studentCourse) {
        final StudentCourse temp = this.studentCourseRepo.findById(studentCourseId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(studentCourse.getCourse());
        temp.setStudent(studentCourse.getStudent());
        temp.setTutor(studentCourse.getTutor());
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
        final Student student = Student.builder().id(studentId).build();
        this.studentCourseRepo.deleteByStudent(student);
        return "All studentCourse with student: "+student.getName()+" deleted";
    }

    public String deleteByCourse(Long courseId) {
        final Course course = Course.builder().id(courseId).build();
        this.studentCourseRepo.deleteByCourse(course);
        return "All studentCourse with course: "+course.getName()+" deleted";
    }
}
