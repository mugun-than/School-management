package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.service.SchoolService;
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
    public ResponseDTO createSchool(@RequestBody School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping()
    public ResponseDTO findAll() {
        return this.schoolService.findAll();
    }

    @GetMapping("/{schoolId}")
    public ResponseDTO findById(@PathVariable Long schoolId){
        return this.schoolService.findById(schoolId);
    }

    @PutMapping("/{schoolId}")
    public ResponseDTO updateSchool(@PathVariable Long schoolId, @RequestBody School school) {
        return this.schoolService.updateSchool(schoolId, school);
    }

    @DeleteMapping()
    public ResponseDTO deleteAll() {
        return this.schoolService.deleteAll();
    }

    @DeleteMapping("/{schoolId}")
    public ResponseDTO deleteById(@PathVariable Long schoolId) {
        return this.schoolService.deleteById(schoolId);
    }
}
