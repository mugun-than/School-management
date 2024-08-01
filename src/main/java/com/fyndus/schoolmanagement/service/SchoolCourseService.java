package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.SchoolCourseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
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

    public ResponseDTO createSchoolCourseByDTO(SchoolCourseDTO schoolCourseDTO) {
        final School school = this.schoolRepo.findByName(schoolCourseDTO.getSchoolName());
        if(school == null) {
            throw new NullPointerException();
        }
        final Course course = this.courseRepo.findByName(schoolCourseDTO.getCourseName());
        if(course == null) {
            throw new NullPointerException();
        }
        final SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setSchool(school);
        schoolCourse.setCourse(course);
        schoolCourse.setCreatedAt(Instant.now());
        return ResponseDTO.builder().data(this.schoolCourseRepo.save(schoolCourse)).message(ResponseMessage.CREATED).build();
    }

    public ResponseDTO findById(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder().data(schoolCourse).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findAll() {
        List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAll();
        if(schoolCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateSchoolCourse(Long schoolCourseId, SchoolCourseDTO schoolCourseDTO) {
        final School school = this.schoolRepo.findByName(schoolCourseDTO.getSchoolName());
        if(school == null) {
            throw new NullPointerException();
        }
        final Course course = this.courseRepo.findByName(schoolCourseDTO.getCourseName());
        if(course == null) {
            throw new NullPointerException();
        }
        final SchoolCourse temp = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(course);
        temp.setSchool(school);
        return ResponseDTO.builder().data(this.schoolCourseRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }

    public ResponseDTO deleteAll() {
        List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAll();
        if(schoolCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.schoolCourseRepo.deleteAll();
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteById(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        this.schoolCourseRepo.deleteById(schoolCourseId);
        return ResponseDTO.builder().data(schoolCourse).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteBySchool(Long schoolId) {
        final School school = schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        this.schoolCourseRepo.deleteBySchool(school);
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.DELETED).build();
    }
}
