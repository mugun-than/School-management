package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(this.courseService.createCourse(course), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long courseId) {
        return new ResponseEntity<>(this.courseService.findById(courseId), HttpStatus.OK);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<ResponseDTO> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        return new ResponseEntity<>(this.courseService.updateCourse(courseId, course), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll(){
        return new ResponseEntity<>(this.courseService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long courseId) {
        return new ResponseEntity<>(this.courseService.deleteById(courseId), HttpStatus.OK);
    }
}
