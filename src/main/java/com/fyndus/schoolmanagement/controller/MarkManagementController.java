package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.entity.MarkManagement;
import com.fyndus.schoolmanagement.service.MarkManagementService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@RestController
@RequestMapping("/mark-management")
public class MarkManagementController {

    private final MarkManagementService markManagementService;

    public MarkManagementController(MarkManagementService markManagementService) {
        this.markManagementService = markManagementService;
    }

    @PostMapping("/{studentId}/{courseId}")
    public String calculateMarkForStudentByCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return this.markManagementService.calculateMarkForStudent(studentId, courseId);
    }

    @GetMapping()
    public List<MarkManagement> findAll() {
        return this.markManagementService.findAll();
    }

    @PutMapping("/{studentId}/{courseId}")
    public String updateStudentMarkByCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        return this.markManagementService.updateStudentMarkByCourse(studentId, courseId);
    }

    @DeleteMapping()
    public String deleteAll() {
        return this.markManagementService.deleteAll();
    }


    @DeleteMapping("/{markManagementId}")
    public String deleteById(@PathVariable Long markMangementId) {
        return this.markManagementService.deleteById(markMangementId);
    }
}
