package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.TutorDTO;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.service.TutorService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping()
    public ResponseDTO createTutor(@RequestBody TutorDTO tutorDTO) {
        return this.tutorService.createTutor(tutorDTO);
    }

    @GetMapping()
    public ResponseDTO findAll() {
        return this.tutorService.findAll();
    }

    @GetMapping("/{tutorId}")
    public ResponseDTO findById(@PathVariable Long tutorId) {
        return this.tutorService.findById(tutorId);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseDTO findBySchool(@PathVariable Long schoolId) {
        return this.tutorService.findBySchool(schoolId);
    }

    @PutMapping("/{tutorId}")
    public ResponseDTO updateTutor(@PathVariable Long tutorId, @RequestBody TutorDTO tutorDTO) {
        return this.tutorService.updateTutor(tutorId, tutorDTO);
    }

    @DeleteMapping()
    public ResponseDTO deleteAll() {
        return this.tutorService.deleteAll();
    }

    @DeleteMapping("/{tutorId}")
    public ResponseDTO deleteById(@PathVariable Long tutorId) {
        return this.tutorService.deleteById(tutorId);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public ResponseDTO deleteBySchool(@PathVariable Long schoolId) {
        return this.tutorService.deleteBySchool(schoolId);
    }
}
