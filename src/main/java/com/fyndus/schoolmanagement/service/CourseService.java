package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course createCourse(Course course) {
        course.setCreatedAt(Instant.now());
        return this.courseRepo.save(course);
    }

    public Course findById(Long courseId) {
        return this.courseRepo.findById(courseId).orElse(null);
    }

    public List<Course> findAll() {
        return this.courseRepo.findAll();
    }

    public Course updateCourse(Long courseId, Course course) {
        final Course temp = this.courseRepo.findById(courseId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setName(course.getName());
        return this.courseRepo.save(temp);
    }

    public String deleteAll() {
        this.courseRepo.deleteAll();
        return "All courses deleted";
    }

    public String deleteById(Long courseId) {
        this.courseRepo.deleteById(courseId);
        return "Course with id: "+courseId+" deleted";
    }
}
