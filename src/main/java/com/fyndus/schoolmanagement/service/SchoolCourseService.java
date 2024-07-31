package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.SchoolCourseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
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
        final Course course = this.courseRepo.findByName(schoolCourseDTO.getCourseName());
        if(school == null) {
            return ResponseDTO.builder().message("School - "+schoolCourseDTO.getSchoolName()+" "+ResponseMessage.NOT_FOUND).build();
        }
        else if(course == null) {
            return ResponseDTO.builder().message("Course - "+schoolCourseDTO.getCourseName()+" "+ResponseMessage.NOT_FOUND).build();
        }
        final SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setSchool(school);
        schoolCourse.setCourse(course);
        schoolCourse.setCreatedAt(Instant.now());
        return ResponseDTO.builder().data(this.schoolCourseRepo.save(schoolCourse)).message(ResponseMessage.CREATED).build();
    }

    public ResponseDTO findById(Long schoolCourseId) {
        final SchoolCourse schoolCourse;
        try {
            schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("SchoolCourse id: "+schoolCourseId+" "+ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder().data(schoolCourse).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findBySchool(Long schoolId) {
        final School school;
        try {
            school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+schoolId+" "+ResponseMessage.NOT_FOUND).build();
        }
        final List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findBySchool(school);
        if(schoolCourses.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findAll() {
        List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAll();
        if(schoolCourses.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateSchoolCourse(Long schoolCourseId, SchoolCourseDTO schoolCourseDTO) {
        final School school = schoolRepo.findByName(schoolCourseDTO.getSchoolName());
        final Course course = courseRepo.findByName(schoolCourseDTO.getCourseName());
        final SchoolCourse temp;
        if(school == null) {
            return ResponseDTO.builder().message("School - "+schoolCourseDTO.getSchoolName()+" "+ResponseMessage.NOT_FOUND).build();
        }
        else if(course == null) {
            return ResponseDTO.builder().message("Course - "+schoolCourseDTO.getCourseName()+" "+ResponseMessage.NOT_FOUND).build();
        }
        try {
            temp = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(course);
        temp.setSchool(school);
        return ResponseDTO.builder().data(this.schoolCourseRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }

    public ResponseDTO deleteAll() {
        List<SchoolCourse> schoolCourses = this.schoolCourseRepo.findAll();
        if(schoolCourses.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        this.schoolCourseRepo.deleteAll();
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteById(Long schoolCourseId) {
        final SchoolCourse schoolCourse;
        try {
            schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        this.schoolCourseRepo.deleteById(schoolCourseId);
        return ResponseDTO.builder().data(schoolCourse).message(ResponseMessage.DELETED).build();
    }

    public ResponseDTO deleteBySchool(Long schoolId) {
        final School school;
        final List<SchoolCourse> schoolCourses;
        try {
            school = schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message("School id: "+schoolId+"-"+ResponseMessage.NOT_FOUND).build();
        }
        schoolCourses = this.schoolCourseRepo.findAllBySchool(school);
        if(schoolCourses.isEmpty()) {
            return ResponseDTO.builder().message("School id: "+schoolId+"-"+ResponseMessage.EMPTY).build();
        }
        this.schoolCourseRepo.deleteBySchool(school);
        return ResponseDTO.builder().data(schoolCourses).message(ResponseMessage.DELETED).build();
    }
}
