package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepo;

    public SchoolService(SchoolRepository schoolRepo) {
        this.schoolRepo = schoolRepo;
    }
}
