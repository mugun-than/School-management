package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createSchool(@RequestBody School school) {
        return new ResponseEntity<>(this.schoolService.createSchool(school), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.schoolService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long schoolId){
        return new ResponseEntity<>(this.schoolService.findById(schoolId), HttpStatus.OK);
    }

    @PutMapping("/{schoolId}")
    public ResponseEntity<ResponseDTO> updateSchool(@PathVariable Long schoolId, @RequestBody School school) {
        return new ResponseEntity<>(this.schoolService.updateSchool(schoolId, school), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.schoolService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.schoolService.deleteById(schoolId), HttpStatus.OK);
    }
}
