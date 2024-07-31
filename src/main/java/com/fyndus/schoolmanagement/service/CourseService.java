package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public ResponseDTO createCourse(Course course) {
        course.setCreatedAt(Instant.now());
        return ResponseDTO.builder().data(this.courseRepo.save(course)).message(course.getName()+" "+ResponseMessage.CREATED).build();
    }

    public ResponseDTO findById(Long courseId) {
        final Course course;
        try {
            course = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        }catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder().data(course).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO findAll() {
        final List<Course> courses = this.courseRepo.findAll();
        if(courses.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder()
         .data(courses)
         .message(ResponseMessage.FOUND)
         .build();
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setData(courses);
//        responseDTO.setMessage(ResponseMessage.FOUND);
    }

    public ResponseDTO updateCourse(Long courseId, Course course) {
        final Course temp;
        try {
            temp = this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            return ResponseDTO.builder().message(course.getName() + "-" + ResponseMessage.NOT_FOUND).build();
        }
        temp.setUpdatedAt(Instant.now());
        temp.setName(course.getName());
        return ResponseDTO.builder().data(this.courseRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }

    public ResponseDTO deleteAll() {
        final List<Course> courses = this.courseRepo.findAll();
        if(courses.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        final ResponseDTO responseDTO = ResponseDTO.builder().data(courses).message(ResponseMessage.DELETED).build();
        this.courseRepo.deleteAll();
        return responseDTO;
    }

    public ResponseDTO deleteById(Long courseId) {
        final ResponseDTO responseDTO;
        try {
            responseDTO = ResponseDTO.builder().data(this.courseRepo.findById(courseId).orElseThrow(NullPointerException::new)).message("Course with id: "+courseId+" "+ResponseMessage.DELETED).build();
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        this.courseRepo.deleteById(courseId);
        return responseDTO;
    }
}
