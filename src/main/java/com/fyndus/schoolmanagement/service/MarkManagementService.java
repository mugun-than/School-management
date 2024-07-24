package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.MarkManagementRepository;
import org.springframework.stereotype.Service;

@Service
public class MarkManagementService {

    private final MarkManagementRepository markManagementRepo;

    public MarkManagementService(MarkManagementRepository markManagementRepo) {
        this.markManagementRepo = markManagementRepo;
    }
}
