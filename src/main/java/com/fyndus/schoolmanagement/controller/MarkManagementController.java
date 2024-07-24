package com.fyndus.schoolmanagement.controller;

import com.fyndus.schoolmanagement.service.MarkManagementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mark-management")
public class MarkManagementController {

    private final MarkManagementService markManagementService;

    public MarkManagementController(MarkManagementService markManagementService) {
        this.markManagementService = markManagementService;
    }
}
