package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.SchoolCourseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SchoolCourseService {

    private final SchoolCourseRepository schoolCourseRepo;
    private final SchoolRepository schoolRepo;
    private final CourseRepository courseRepo;

    public SchoolCourseService(SchoolCourseRepository schoolCourseRepo, CourseRepository courseRepo, SchoolRepository schoolRepo) {
        this.schoolCourseRepo = schoolCourseRepo;
        this.schoolRepo = schoolRepo;
        this.courseRepo = courseRepo;
    }

    public SchoolCourse createSchoolCourseByDTO(SchoolCourseDTO schoolCourseDTO) {
        School school = schoolRepo.findByName(schoolCourseDTO.getSchoolName());
        Course course = courseRepo.findByName(schoolCourseDTO.getCourseName());

        System.out.println(schoolCourseDTO.getSchoolName()+"\n"+schoolCourseDTO.getCourseName());
        System.out.println(school.getName()+"\n"+course.getName());
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setSchool(school);
        schoolCourse.setCourse(course);
        schoolCourse.setCreatedAt(Instant.now());
        return this.schoolCourseRepo.save(schoolCourse);
    }

    public SchoolCourse findById(Long schoolCourseId) {
        return this.schoolCourseRepo.findById(schoolCourseId).orElse(null);
    }

    public List<SchoolCourse> findBySchool(Long schoolId) {
        final School school = School.builder().id(schoolId).build();
        return this.schoolCourseRepo.findBySchool(school);
    }

    public List<SchoolCourse> findAll() {
        return this.schoolCourseRepo.findAll();
    }

    public SchoolCourse updateSchoolCourse(Long schoolCourseId, SchoolCourseDTO schoolCourseDTO) {
        final School school = schoolRepo.findByName(schoolCourseDTO.getSchoolName());
        final Course course = courseRepo.findByName(schoolCourseDTO.getCourseName());
        final SchoolCourse temp = this.schoolCourseRepo.findById(schoolCourseId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(course);
        temp.setSchool(school);
        return this.schoolCourseRepo.save(temp);
    }

    public String deleteAll() {
        this.schoolCourseRepo.deleteAll();
        return "All schoolCourse deleted";
    }

    public String deleteById(Long schoolCourseId) {
        this.schoolCourseRepo.deleteById(schoolCourseId);
        return "SchoolCourse with id: "+schoolCourseId+" deleted";
    }

    public String deleteBySchool(Long schoolId) {
        final School school = schoolRepo.findById(schoolId).orElse(null);
        this.schoolCourseRepo.deleteBySchool(school);
        assert school != null;
        return "SchoolCourse belonging to school: "+school.getName()+" deleted";
    }
}
