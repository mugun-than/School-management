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
        return this.schoolService.createSchool(school);
    }

    @GetMapping()
    public List<School> findAll() {
        return this.schoolService.findAll();
    }

    @GetMapping("/{schoolId}")
    public School findById(@PathVariable Long schoolId){
        return this.schoolService.findById(schoolId);
    }

    @PutMapping("/{schoolId}")
    public School updateSchool(@PathVariable Long schoolId, @RequestBody School school) {
        return this.schoolService.updateSchool(schoolId, school);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.schoolService.deleteAll();
    }

    @DeleteMapping("/{schoolId}")
    public String deleteById(@PathVariable Long schoolId) {
        return this.schoolService.deleteById(schoolId);
    }
}
