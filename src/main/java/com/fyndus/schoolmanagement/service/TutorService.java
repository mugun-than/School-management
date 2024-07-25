package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepo;

    public TutorService(TutorRepository tutorRepo) {
        this.tutorRepo = tutorRepo;
    }

    public Tutor createTutor(Tutor tutor) {
        tutor.setCreatedAt(Instant.now());
        return this.tutorRepo.save(tutor);
    }

    public Tutor findById(Long tutorId) {
        return this.tutorRepo.findById(tutorId).orElse(null);
    }

    public List<Tutor> findBySchool(School school) {
        return this.tutorRepo.findBySchool(school);
    }

    public List<Tutor> findAll() {
        return this.tutorRepo.findAll();
    }

    public Tutor updateTutor(Long tutorId, Tutor tutor) {
        final Tutor temp = this.tutorRepo.findById(tutorId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setAddress(tutor.getAddress());
        temp.setName(tutor.getName());
        temp.setSchool(tutor.getSchool());
        return this.tutorRepo.save(temp);
    }

    public String deleteAll() {
        this.tutorRepo.deleteAll();
        return "All tutor deleted";
    }

    public String deleteById(Long tutorId) {
        this.tutorRepo.deleteById(tutorId);
        return "Tutor with id: "+tutorId+" deleted";
    }

    public String deleteBySchool(School school) {
        this.tutorRepo.deleteBySchool(school);
        return "Tutor of school: "+school.getName()+" deleted";
    }
}

