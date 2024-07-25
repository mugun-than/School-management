package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.MarkManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mark-management")
public class MarkManagementController {

    private final MarkManagementService markManagementService;

    public MarkManagementController(MarkManagementService markManagementService) {
        this.markManagementService = markManagementService;
    }

    @PostMapping("/{studentId}/{courseId}")
    public String calculateMarkForStudentByCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return markManagementService.calculateMarkForStudent(studentId, courseId);
    }

    @PutMapping("{studentId}/{courseId}")
    public String updateStudentMarkByCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        return markManagementService.updateStudentMarkByCourse(studentId, courseId);
    }

}
