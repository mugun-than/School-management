package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.SchoolCourseDTO;
import com.fyndus.schoolmanagement.entity.SchoolCourse;
import com.fyndus.schoolmanagement.service.SchoolCourseService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school-course")
public class SchoolCourseController {

    private final SchoolCourseService schoolCourseService;

    public SchoolCourseController(SchoolCourseService schoolCourseService) {

        this.schoolCourseService = schoolCourseService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createSchoolCourse(@RequestBody SchoolCourseDTO schoolCourseDTO) {
        return new ResponseEntity<>(this.schoolCourseService.createSchoolCourseByDTO(schoolCourseDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.schoolCourseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.schoolCourseService.findById(schoolCourseId), HttpStatus.OK);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> findBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.schoolCourseService.findBySchool(schoolId), HttpStatus.OK);
    }

    @PutMapping("/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> updateSchoolCourse(@PathVariable Long schoolCourseId, @RequestBody SchoolCourseDTO schoolCourseDTO) {
        return new ResponseEntity<>(this.schoolCourseService.updateSchoolCourse(schoolCourseId, schoolCourseDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.schoolCourseService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{schoolCourseId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long schoolCourseId) {
        return new ResponseEntity<>(this.schoolCourseService.deleteById(schoolCourseId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> deleteBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.schoolCourseService.deleteBySchool(schoolId), HttpStatus.OK);
    }
}
