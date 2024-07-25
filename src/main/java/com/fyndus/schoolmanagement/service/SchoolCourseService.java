package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SchoolCourseService {

    private final SchoolCourseRepository schoolCourseRepo;

    public SchoolCourseService(SchoolCourseRepository schoolCourseRepo) {
        this.schoolCourseRepo = schoolCourseRepo;
    }

    public SchoolCourse createSchoolCourse(SchoolCourse schoolCourse) {
        schoolCourse.setCreatedAt(Instant.now());
        return this.schoolCourseRepo.save(schoolCourse);
    }

    public SchoolCourse findById(Long schoolCourseId) {
        return this.schoolCourseRepo.findById(schoolCourseId).orElse(null);
    }

    public List<SchoolCourse> findBySchool(School school) {
        return this.schoolCourseRepo.findBySchool(school);
    }

    public List<SchoolCourse> findAll() {
        return this.schoolCourseRepo.findAll();
    }

    public SchoolCourse updateSchoolCourse(Long schoolCourseId, SchoolCourse schoolCourse) {
        final SchoolCourse temp = this.schoolCourseRepo.findById(schoolCourseId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(schoolCourse.getCourse());
        temp.setSchool(schoolCourse.getSchool());
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

    public String deleteBySchool(School school) {
        this.schoolCourseRepo.deleteBySchool(school);
        return "SchoolCourse belonging to school: "+school.getName()+" deleted";
    }
}
