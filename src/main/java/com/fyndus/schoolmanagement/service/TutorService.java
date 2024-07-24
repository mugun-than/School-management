package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepo;

    public TutorService(TutorRepository tutorRepo) {
        this.tutorRepo = tutorRepo;
    }
}
