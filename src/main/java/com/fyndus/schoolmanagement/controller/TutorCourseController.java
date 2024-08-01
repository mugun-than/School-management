package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.TutorCourseDTO;
import com.fyndus.schoolmanagement.entity.TutorCourse;
import com.fyndus.schoolmanagement.service.TutorCourseService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor/course")
public class TutorCourseController {

    private final TutorCourseService tutorCourseService;

    public TutorCourseController(TutorCourseService tutorCourseService) {
        this.tutorCourseService = tutorCourseService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createTutorCourse(@RequestBody TutorCourseDTO tutorCourseDTO) {
        return new ResponseEntity<>(this.tutorCourseService.createTutorCourse(tutorCourseDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.tutorCourseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{tutorCourseId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long tutorCourseId) {
        return new ResponseEntity<>(this.tutorCourseService.findById(tutorCourseId), HttpStatus.OK);
    }

    @GetMapping("/school-course/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> findByCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.tutorCourseService.findByCourse(schoolCourseId), HttpStatus.OK);
    }

    @PutMapping("/{tutorCourseId}")
    public ResponseEntity<ResponseDTO> updateTutorCourse(@PathVariable Long tutorCourseId, @RequestBody TutorCourseDTO tutorCourseDTO) {
        return new ResponseEntity<>(this.tutorCourseService.updateTutorCourse(tutorCourseId, tutorCourseDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.tutorCourseService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{tutorCourseId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long tutorCourseId) {
        return new ResponseEntity<>(this.tutorCourseService.deleteById(tutorCourseId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/school-course/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> deleteByCourse(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.tutorCourseService.deleteBySchoolCourse(schoolCourseId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/tutor/{tutorId}")
    public ResponseEntity<ResponseDTO> deleteByTutor(@PathVariable Long tutorId) {
        return new ResponseEntity<>(this.tutorCourseService.deleteByTutor(tutorId), HttpStatus.OK);
    }
}