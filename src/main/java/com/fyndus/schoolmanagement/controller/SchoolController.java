package com.fyndus.schoolmanagement.controller;

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
    public School createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @GetMapping()
    public List<School> findAll() {
        return schoolService.findAll();
    }

    @GetMapping("/{schoolId}")
    public School findById(@PathVariable Long schoolId){
        return schoolService.findById(schoolId);
    }

    @PutMapping("/{schoolId}")
    public School updateSchool(@PathVariable Long schoolId, @RequestBody School school) {
        return schoolService.updateSchool(schoolId, school);
    }

    @DeleteMapping()
    public String deleteAll() {
        return schoolService.deleteAll();
    }

    @DeleteMapping("/{schoolId}")
    public String deleteById(@PathVariable Long schoolId) {
        return schoolService.deleteById(schoolId);
    }
}
