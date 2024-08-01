package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.TutorDTO;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.service.TutorService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO> createTutor(@RequestBody TutorDTO tutorDTO) {
        return new ResponseEntity<>(this.tutorService.createTutor(tutorDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return new ResponseEntity<>(this.tutorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long tutorId) {
        return new ResponseEntity<>(this.tutorService.findById(tutorId), HttpStatus.OK);
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> findBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.tutorService.findBySchool(schoolId), HttpStatus.OK);
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<ResponseDTO> updateTutor(@PathVariable Long tutorId, @RequestBody TutorDTO tutorDTO) {
        return new ResponseEntity<>(this.tutorService.updateTutor(tutorId, tutorDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteAll() {
        return new ResponseEntity<>(this.tutorService.deleteAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long tutorId) {
        return new ResponseEntity<>(this.tutorService.deleteById(tutorId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/school/{schoolId}")
    public ResponseEntity<ResponseDTO> deleteBySchool(@PathVariable Long schoolId) {
        return new ResponseEntity<>(this.tutorService.deleteBySchool(schoolId), HttpStatus.OK);
    }
}
